//驼峰转下划线并把字母大写
function toUpper(str) {
	str = str.replace(/([A-Z])/g, "_$1").toLowerCase()
	return str.toUpperCase()
	// return str
}
//下划线转驼峰
function toLower(s) {
	s = s.toLowerCase()
	var a = s.split("_");
	var result = a[0];
	for (var i = 1; i < a.length; i++) {
		result = result + a[i].slice(0, 1).toUpperCase() + a[i].slice(1);
	}
	return result
}


// console.log(toUpper('treatmentMeasures'))
// console.log(toUpper('exceptionDescribe'))
console.log(toUpper('addQualityFlag'))
// console.log(toUpper('exceptionOperator'))
console.log(toLower('MATERIEL_AUDITOR'))
console.log("t_materiel_Auditor".toUpperCase())


var str = "Hello world! wo shi oll"
// console.log(str.indexOf("o")) //4
// console.log(str.lastIndexOf("o")) //20
// console.log(str.indexOf("Hello")) //0
// console.log(str.indexOf("World") == -1) //true
// console.log(str.indexOf("world") == -1) //false
