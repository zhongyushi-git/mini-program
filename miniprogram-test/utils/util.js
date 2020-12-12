//是否为空
const isNotBlank = val => {
	return val != null && val != ''
}
//对象转url拼接
const ObjToUrl = val => {
	const keys = Object.keys(data)
	let params = '?'
	for (let i = 0; i < keys.length; i++) {
		const key = keys[i]
		let value = data[key]
		if (value != null && value != '') {
			if (i != 0) {
				params += '&'
			}
			//对特殊字符进行转义
			value = encodeURIComponent(value)
			params += key + '=' + value
		}
	}
	return params
}
//消息提示框(无图标)
function noToast(msg) {
	wx.showToast({
		title: msg,
		icon: 'none',
		duration: 2000
	})
}
//消息提示框(有图标)
function toast(msg) {
	wx.showToast({
		title: msg,
		duration: 2000
	})
}

module.exports = {
	isNotBlank,
	ObjToUrl,
	noToast,
	toast
}
