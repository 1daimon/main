<template>
	<div class="page">
		<image class="back_ima" src="https://pic1.imgdb.cn/item/6339997216f2c2beb16adafa.png" mode="scaleToFill"></image>
		
		<div class="main">
		<div class="header">
		<div class="back_btn" size="mini" @click="back"> 
				<uni-icons custom-prefix="custom-icon" type="back" size="25"></uni-icons>
		</div>
		<div class="title">我的信息</div>
		<div class="else" @click="go_login">
			登录
		</div>
		</div>
		<div class="per_show">
			<image class="per_ima" :src="person.src" ></image>
		</div>
		<div class="name">
			{{person.name}}
		</div>
		<div>
			<div class="msg">
					<text>昵称</text>
				<text class="content">{{person.name}}</text>
			</div>
			<div class="msg">
				<text>最高分</text>
				<text class="content">45</text>
			</div>
			<div class="msg">
				<text>总场次</text>
				<text class="content">45</text>
			</div>
			<div class="msg">
			<text>邮箱</text>
			<text class="content">{{person.email}}</text>
			</div>
		</div>
		
		<button class="layout" @click="layout">退出登录</button>
		</div>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				
				readylogin:0,
				person:{
					email:'XXX',
					name:'xxx',
					src:'https://pic1.imgdb.cn/item/6338622e16f2c2beb125316b.png'
				},
				styles:{
					width:'100rpx'
				}
			}
		},
		methods:{
			layout(){
				uni.clearStorage();
				this.person={
					email:'XXX',
					name:'xxx',
					src:'https://pic1.imgdb.cn/item/6338622e16f2c2beb125316b.png'
				},
				this.readylogin=0
			},
			go_login(){
				uni.navigateTo({
					url:'/pages/login/login'
				})
			},
			back(){
				uni.navigateTo({
					url:'/pages/index/index'
				})
			}
			
		},
		onShow(){
			var that =this
			uni.getStorage({
				key:'readylogin',
				success: function (res) {
					console.log("res",res.data)
						if(res.data==1){
							that.person.src="https://pic1.imgdb.cn/item/6348460b16f2c2beb1c50215.jpg",
							that.readylogin=res.data
							uni.getStorage({
								key:'name',
								success: function (res) {
									that.person.name=res.data
								}
							})
							uni.getStorage({
								key:'username',
								success: function (res) {
									that.person.email=res.data
								}
							})
						}
						
					}
			})
			console.log(this.person)
		}
	}
</script>

<style scoped>
	.layout{
		margin:15% auto;
		width:80%;
		color:white;
		border-radius: 20rpx;
		background-image: linear-gradient(to right, #33ccff , #0066ff);
	}
	.content{
		margin-left:4%;
	}
	.name{
		margin-top:3%;
		font-size: 50rpx;
		font-weight:600;
		text-align: center;
	}
	.msg{
		font-size:35rpx;
		margin-top:5%;
		margin-bottom:8%;
		margin-left:5%;
	}
	.per_show{
		display: flex;
		margin-top:10%;
	}
	.title{
		font-weight: 600;
		padding:10rpx 0; 
	}
	.per_ima{
		width:150rpx;
		height:150rpx;
		margin:0 auto;
	}
	.main{
		background-color: white;
		width:80%;
		position:fixed;
		border-radius: 15rpx;
		top:10%;
		left:10%;
	}
	.else{
		color:blue;
		padding:10rpx 0;
	}
	.back_btn{
		height:auto;
		width:auto;
		background-color: white;
		border-radius: 50%;
		padding:5rpx;
		border:3rpx solid gray;
	}
	.header{
		justify-content: space-between;
		display: flex;
		flex-wrap: wrap;
		margin-top:10%;
		width:90%;
		margin-left: 5%;
	}
	.page{
		height: 100vh;
	}
	.back_ima{
		height:100%;
		width: 100%;
		position: absolute;
		z-index:-1;
	}
</style>