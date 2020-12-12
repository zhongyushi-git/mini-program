const util = require('./utils/util');

App({
	onLaunch: function() {
		// 登录
		wx.login({
			success: res => {
				// 发送 res.code 到后台换取 openId, sessionKey, unionId
				var that = this;
				wx.request({
					url: this.globalData.serverApi + "/user/checkBind?code=" + res.code,
					success(res) {
						if (res.data.code == 0) {
							//未登录，跳转到登录页进行登录
							wx.reLaunch({
								url: '/pages/login/login?openId=' + res.data.openId
							})
						} else if (res.data.code == 1) {
							//已登录
							that.globalData.token = res.data.token;
						} else if (res.data.code == -1) {
							//获取openId失败 
							util.noToast(res.data.msg)
						}

					}
				})
			}
		})
	},
	globalData: {
		userInfo: null,
		token: null,
		serverApi: "http://localhost:8080/api"
	}
})
