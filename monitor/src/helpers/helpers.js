export function getShortdate(d) {
  var month = d.getMonth();
  var day = d.getDate().toString().padStart(2, "0");
  var year = d.getFullYear();
  year = year.toString().substr(-2);
  month = (month + 1).toString().padStart(2, "0");
  return month + "/" + day + "/" + year;
}

export const getISODate = (d) => {
  if (!d) return d;
  return new Date(d).toISOString();
};

export const getTwoDecimalsPercentage = (n) => {
  return n.toFixed(2) + "%";
};
