<template>
	<button 
	            
	            type='primary' lang="zh_CN" @click="getUserProfile">登录
	            </button>
</template>

<script>
	export default{
		methods:{
			/*login(){
				uni.getProvider({
							service: 'oauth',
							success: function(res) {
								console.log(res.provider);
								// 手机app支持微信、qq和微博等
								if (~res.provider.indexOf('weixin')) {
									console.log('weixin');
									uni.login({
										provider: 'weixin',
										success: function(loginRes) {
											console.log(JSON.stringify(loginRes));
											// 获取用户信息
											uni.getUserInfo({
												provider: 'weixin',
												success: function(infoRes) {
													console.log('-------获取微信用户所有-----');
													console.log(JSON.stringify(infoRes.userInfo));
													const userInfo = infoRes.userInfo
													getApp().globalData.userInfo = userInfo;
													getApp().globalData.wxCode = userInfo.nickName;
													console.log(getApp().globalData.userInfo);
													uni.switchTab({
														url: '/pages/my/my'
													})
												}
											});
										},
										fail: function(res) {
											console.log(res)
											uni.showToast({
												icon: 'none',
												title: '登陆失败,请确保已安装或已登录微信',
												//title: res.errMsg,
												duration: 2000
											});
											setTimeout(function() {
												uni.switchTab({
													url: '/pages/my/my'
												})
											}, 2000)
										}
									});
								}
							}
						})

		}*/
		getUserProfile() {
					//推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息                                              均需用户确认
						// 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
						uni.getUserProfile({
							desc: '用于获取您的个人信息', // s声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
							success: res1 => {
								uni.showLoading({
									title: '登录中'
								});
								//业务逻辑
								// console.log(res1);
								wx.login({
									success: res => {
										if (res.code) {
											//发起网络请求
		                                    //请求后端给的登录接口，把res.code等相关参数带上
											console.log(res)
										} else {
											// console.log('登录失败！' + res.errMsg)
										}
									}
								})
							}
						})
					},
		
		
	}
	}
	
</script>

<style>
</style>