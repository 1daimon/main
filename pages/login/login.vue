<template>
	<div class="page">
		<image @click="back" class="home" src="https://pic1.imgdb.cn/item/6339401016f2c2beb1e1a7c6.png" mode=""></image>
		<image class="back_ima" src="https://pic1.imgdb.cn/item/6339997216f2c2beb16adafa.png" mode="scaleToFill"></image>
		<image class="login_back_ima" src="https://pic1.imgdb.cn/item/6339975d16f2c2beb16685ef.png"  ></image>
		<uni-popup ref="message" type="message">
						<uni-popup-message type="error" :message="messageText" :duration="2000"></uni-popup-message>
					</uni-popup>
		<div class="login_msg">
			<div class="title">
				<text>登录账号</text>
			</div>
			<div class="tip">
				<text>还没有账号？</text>
				<text style="color:blue;margin-left:30rpx" @click="go_register">现在注册</text>
			</div>
			<div class="login_user">
					<div class="input_msg">
				<div class="input_title">邮箱</div>
			<uni-easyinput class="input" v-model="form.username" :styles="styles"  placeholder="请输入邮箱"></uni-easyinput>
			</div>
				<div class="input_msg">
					<div class="input_title">密码</div>
					<uni-easyinput class="input" type="password" v-model="form.password" :styles="styles"  placeholder="请输入密码"></uni-easyinput>
				</div>
					<button @click="login_to" class="login_btn">立即登录</button>
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
				form:{
					username:'',
					password:''
				},
				messageText:'',
				styles:{
					width:'80%',
					border:'0',
					background:'rgba(0,0,0,1)',
					
				},
				username:'',
				password:'',
				name:''
			} 
		},
		methods:{
			back(){
				uni.navigateTo({
					url:'/pages/index/index'
				})
			},
			messageToggle() {
							
							this.messageText = `账号或密码错误`
							this.$refs.message.open()
						},
			login_to(){
				uniCloud.callFunction({
					name:'login',
					data:this.form,
					success:(e)=>{
						console.log(e.result)
						if(e.result!=null){
							this.username=e.result.username,
							this.password=e.result.password,
							this.name=e.result.name
							uni.setStorage({
								key: 'username',
								data: this.username,
								success: function () {
									console.log('success');
								}
							});
							uni.setStorage({
								key: 'password',
								data: this.password,
								success: function () {
									console.log('success');
								}
							});
							uni.setStorage({
								key: 'name',
								data: this.name,
								success: function () {
									console.log('success');
								}
							});
							uni.setStorage({
								key: 'readylogin',
								data: 1,
								success: function () {
									console.log('success');
								}
							});
							uni.navigateTo({
								url:'/pages/index/index'
							})
						}else{
							this.messageToggle()
						}
					}
				})
			},
			go_register(){
				uni.navigateTo({
					url:'/pages/register/register'
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
	}
	#foot1{
		text-align: center;
	}
	.input_msg{
		margin-top:30rpx;
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
	.home{
		height:90rpx;
		width:90rpx;
		position: fixed;
		left:2%;
		top:2%;
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