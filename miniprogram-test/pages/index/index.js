Page({
	data: {
		username:''
	},
	onLoad(options){
		this.setData({
			username:wx.getStorageSync("username")
		})
		
	}
	
})
