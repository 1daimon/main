
	<template>
		<div class="page">
			<image class="back_ima" src="https://pic1.imgdb.cn/item/6339997216f2c2beb16adafa.png" mode="scaleToFill"></image>
			<image class="login_back_ima" src="https://pic1.imgdb.cn/item/6339975d16f2c2beb16685ef.png"  ></image>
			<div class="login_msg">
				<div class="title">
					<text>注册账号</text>
				</div>
				<div class="tip">
					<text>您已经有账号啦？</text>
					<text style="color:blue;margin-left:30rpx" @click="go_login">现在登录</text>
				</div>
				<div class="login_user">
					<div class="input_msg">
						<div class="input_title">电话</div>
						<uni-easyinput class="input" type="password" v-model="value" :styles="styles"  placeholder="请输入电话"@input="input"></uni-easyinput>
					</div>
						<div class="input_msg">
					<div class="input_title">邮箱</div>
				<uni-easyinput class="input" v-model="value" :styles="styles"  placeholder="请输入邮箱"@input="input"></uni-easyinput>
				</div>
					<div class="input_msg"> 
						<div class="input_title">密码</div>
						<uni-easyinput class="input" type="password" v-model="value" :styles="styles"  placeholder="请输入密码"@input="input"></uni-easyinput>
					</div>
					
						<button class="login_btn">现在加入</button>
				</div>
				<div class="foot">
					<div id="foot1">点击加入</div>
					<div id="foot1">即表示您同意使用条款和隐私政策</div>
				</div>
			</div>
		</div>
	</template>
	 
	<script>
		export default{
			data(){
				return {
					styles:{
						width:'80%',
						border:'0',
						background:'rgba(0,0,0,1)',
						
					}
				} 
			},
			methods:{
				go_login(){
					uni.navigateTo({
						url:'/pages/login/login '
					})
				}
			}
		}
	</script>
	
	<style scoped>
		.login_btn{
			border-radius: 25rpx;
			width:80%;
			margin:40rpx auto;
			background-color: aqua;
			color:white;
			font-size:30rpx;
			padding:0rpx;
		}
		#foot1{
			text-align: center;
		}
		.input_msg{
			margin-top:2%;
		}
		.input_title{
			margin-bottom:20rpx;
		}
		.foot{
			width:60%;
			margin-left:20%;
			bottom:15%;
			position:fixed;
			font-size: 25rpx;
		}
		.page{
			height:100vh;
		}
		.back_ima{
			height:100%;
			width:100%;
			position:fixed;
			z-index: -1;
		}
		.login_back_ima{
			height:80%;
			width:70%;
			position:fixed;
			z-index:0;
			left:15%;
			top:10%;
			border-radius: 30rpx;
		}
		.title{
			font-size:45rpx;
			font-weight: 600;
			color:white;
			position: fixed;
			top:15%;
			left:20%;
		}
		.tip{
			position: fixed;
			top:28%;
			left:20%;
			font-size:25rpx;
		}
		.login_user{
			top:32%;
			left:19%;
			position: fixed;
			width:60%;
		}
		.input_title{
			font-size: 25rpx;
			color:blue;
			
		}
	</style>
