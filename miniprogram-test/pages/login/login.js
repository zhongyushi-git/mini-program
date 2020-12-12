const app = getApp();
const request = require('../../utils/request');
const util = require('../../utils/util');

Page({

	data: {
		user: {
			username: '',
			password: ''
		},
		openId:''
	},
	onLoad: function(options) {
		this.data.openId=options.openId
	},
	formSubmit(e) {
		const user = e.detail.value
		if (!util.isNotBlank(user.username) || !util.isNotBlank(user.password)) {
			util.noToast('请输入用户名或密码')
			return
		}
		user.openId = this.data.openId
		this.data.user = user
		console.log(this.data.user)
		request.post("/user/loginForBind", this.data.user, function(res) {
			console.log(res)
			if (res.code == 1) {
				wx.setStorageSync("username", res.data.username)
				wx.reLaunch({
					url: '/pages/index/index'
				})
			} else {
				util.noToast('用户名或密码错误')
			}
		})

	}


})
