export function getShortdate(d) {
  var month = d.getMonth();
  var day = d.getDate().toString().padStart(2, "0");
  var year = d.getFullYear();
  year = year.toString().substr(-2);
  month = (month + 1).toString().padStart(2, "0");
  return month + "/" + day + "/" + year;
}

export const getLocaleString = (d) => {
  if (!d) return d;
  return new Date(d).toLocaleString();
};

export const getTwoDecimalsPercentage = (n) => {
  return n.toFixed(2) + "%";
};

export const getZeroDecimalsPercentage = (n) => {
  return n.toFixed(0) + "%";
};
