/**
 * @param {Array} arr
 * @param {number} size
 * @return {Array}
 */
var chunk = function(arr, size) {
    const charr=[];
  for (let i=0;i<arr.length;i+=size) {
    charr.push(arr.slice(i,i+size));
  }
  return charr;
};