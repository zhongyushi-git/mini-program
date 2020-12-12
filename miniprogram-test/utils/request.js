const app = getApp();
const util = require('./util');


function get(url, callback) {
	wx.request({
		method: 'GET',
		url: app.globalData.serverApi + url,
		// header: {
		// 	'token': app.globalData.token
		// },
		success(res) {
			console.log(res)
			callback(res.data);
		}
	})
}
function post(url, data, callback) {
	wx.request({
		method: 'POST',
		url: app.globalData.serverApi + url,
		data: data,
		// header: {
		// 	'token': app.globalData.token
		// },
		success(res) {
			callback(res.data);
		}
	})
}
function postParam(url, data, callback) {
	const params = util.ObjToUrl(data)
	wx.request({
		method: 'POST',
		url: app.globalData.serverApi + url + params,
		// header: {
		// 	'token': app.globalData.token
		// },
		success(res) {
			callback(res.data);
		}
	})
}

module.exports = {
	get,
	post,
	postParam
}
