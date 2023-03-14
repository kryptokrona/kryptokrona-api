#!/bin/bash
# Versiob 0.1
#
NOW=$(date +%s)
DATE=$(date '+%F %T')


# Colors
RED='\033[1;31m'
YELLOW='\033[1;33m'
BLUE="\\033[38;5;27m"
SEA="\\033[38;5;49m"
GREEN='\033[1;32m'
CYAN='\033[1;36m'
NC='\033[0m'

#emoji codes
CHECK_MARK="${GREEN}\xE2\x9C\x94${NC}"
X_MARK="${RED}\xE2\x9C\x96${NC}"

tabs 25

# curl exists and runs ok

curl --version >/dev/null 2>&1
curl_ok=$?

[[ "$curl_ok" -eq 127 ]] && \
    echo "fatal: curl not installed" && exit 2

GETNODES=$(curl -s -k https://raw.githubusercontent.com/kryptokrona/kryptokrona-nodes-list/master/nodes.json | jq '.nodes[] | { url }' | grep url | awk -F ":" '{ print $2 }' | tr -d '" ' | tr -s '\n' ' ' )
NODES=( $GETNODES )
VERSION="1.1.0"
NODESTOT="0"
NODESUP="0"
# Check Kryptokrona Daemon
echo -e ""
echo -e "$DATE"
echo -e "Node\tStatus\tSynced\tNode height\tNetwork\tConnection IN/OUT\tVersion\tFee"
echo -e "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
for NODE in "${NODES[@]}"
do
   NODESTOT=$(($NODESTOT + 1 ))
   curl -m 6 -s http://"$NODE":11898/getinfo -o $NODE-node
   if [ $? -eq 0 ] ; then
       STATUS=$(cat $NODE-node | jq ."status" | tr -d '"')
       if [[ "$STATUS" == "OK" ]] ; then
           NODESTATUS="${GREEN}$STATUS${NC} ${CHECK_MARK}"
       else
           NODESAATUS="${RED}$STATUS${NC} ${X_MARK}"
       fi
       SYNCED=$(cat $NODE-node | jq ."synced")
       if [[ "$SYNCED" == "true" ]] ; then
           NODESYNC="${GREEN}$SYNCDED${NC} ${CHECK_MARK}"
       else
           NODESSYNC="${RED}$SYNCED${NC} ${X_MARK}"
       fi
       NODEVERSION=$(cat $NODE-node | jq ."version" | tr -d '"')
       if [ "$VERSION" = "$NODEVERSION" ] ; then
           NODEVER="${GREEN}$NODEVERSION${NC} ${CHECK_MARK}"
       else
           NODEVER="${RED}$NODEVERSION${NC} ${X_MARK}"
       fi
       IN=$(cat $NODE-node | jq ."incoming_connections_count")
       if [ $IN -lt "1" ]; then
           NODEIN="${YELLOW}$IN${NC}"
       else
           NODEIN="${GREEN}$IN${NC}"
       fi
       OUT=$(cat $NODE-node | jq ."outgoing_connections_count")
       if [ $OUT -lt "1" ]; then
           NODEOUT="${YELLOW}$OUT${NC}"
       else
           NODEOUT="${GREEN}$OUT${NC}"
       fi
       NODEHEIGHT=$(cat $NODE-node | jq ."height")
       NETHEIGHT=$(cat $NODE-node | jq ."network_height")
       if [ "$NODEHEIGHT" = "$NETHEIGHT" ]; then
           if [ $NODEHEIGHT -lt "1000" ]; then
                                NODEH="${RED}$NODEHEIGHT${NC}"
                                NODES="${YELLOW}$NODE${NC}"
                            else
                                NODEH="${GREEN}$NODEHEIGHT${NC}"
                                NODES="${GREEN}$NODE${NC}"
                            fi
       else
                            NODEH="${RED}$NODEHEIGHT${NC}"
                            NODES="${YELLOW}$NODE${NC}"
       fi
       FEE=$(curl -m 3 -s -k http://"$NODE":11898/feeinfo | jq '.amount')
       echo -e "$NODES\t$NODESTATUS\t$NODESYNC\t$NODEH\t$NETHEIGHT\t$NODEIN/$NODEOUT\t$NODEVER\t$FEE"
       NODESUP=$(($NODESUP + 1 ))
   else
       echo -e "${RED}$NODE${NC} ${X_MARK}"
   fi
rm -f *-node
done
echo -e ""
echo -e "---------------------------------------"
echo -e "Nodes Up: ${GREEN}$NODESUP${NC}/$NODESTOT"
echo -e ""
