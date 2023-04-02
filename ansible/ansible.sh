#! /bin/bash
# this file is intended for manually deploy from your local computer and not in the GitHub Actions workflow

# checks if keypair doesn't exist
if [ ! -f ~/.ssh/id_mjovanc ]; then
    ssh-keygen -t rsa -N "" -f ~/.ssh/id_mjovanc
    eval "$(ssh-agent -s)" &>/dev/null
    ssh-add ~/.ssh/id_mjovanc &>/dev/null
    ssh-copy-id -i ~/.ssh/id_mjovanc.pub root@api.hugin.chat
fi

ansible-playbook provision_vps.yml -i prod.inventory --ask-vault-pass
