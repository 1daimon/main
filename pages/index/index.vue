<template>
	<div class="page">
		 <view class="status_bar">
		        </view>
				<uni-popup ref="message" type="message">
								<uni-popup-message type="warn" :message="messageText" :duration="2000"></uni-popup-message>
							</uni-popup>
		<image class="login_ima" @click="go_per" src="https://pic1.imgdb.cn/item/6338622e16f2c2beb125316b.png" mode="aspectFit"></image>
		
		<image class="ima" src="https://pic1.imgdb.cn/item/63383d8d16f2c2beb1f9cec5.png" mode="scaleToFill"></image>
		<div class="btns" >
		<image @click="into_local" class="btn_ima" src="https://pic1.imgdb.cn/item/6338530d16f2c2beb112fe2a.png" mode="widthFix"></image>
		<image @click="into_ai" class="btn_ima" src="https://pic1.imgdb.cn/item/6338530d16f2c2beb112fe32.png" mode="widthFix"></image>
		<image  @click="into_inter" class="btn_ima" src="https://pic1.imgdb.cn/item/6338514a16f2c2beb110ecaf.png" mode="widthFix"></image>
		<image  @click="into_ai" class="btn_ima" src="https://pic1.imgdb.cn/item/6338530d16f2c2beb112fe39.png" mode="widthFix"></image>
		</div>
	</div>
</template>

<script>
	export default{
		data(){
			return{
				time:5,
				btn_show:true,
				messageText:'',
				readylogin:0,
			}
		},
		
		methods:{
			messageToggle() {
							this.messageText = `请先登录再进行在线对战`
							this.$refs.message.open()
						},
			go_per(){
				uni.navigateTo({
					url:'/pages/person/person'
				})
			},
			into_inter(){
				if(this.readylogin==0){ 
					this.messageToggle()
				}else{
					uni.navigateTo({
						url:'/pages/inter/inter'
					})
				}
			},
			into_local(){
					uni.navigateTo({
						url:'/pages/into/into'
					})

			},
			into_ai(){
				uni.navigateTo({
					url:'/pages/ai/ai'
				})
			}
			 
		},
		onShow(){
			uni.getStorage({
				key:'readylogin',
				success: function (res) {
					console.log("res",res)
						if(res.data==1){
							this.readylogin=res.data
						}
						
					}
			})
			setInterval(function(){
				this.time=this.time-1
				if(this.time==0){
					clearInterval()
					this.btn_show=true
				}
			},1000)
			
		}
		}
</script>

<style scoped>
	body{
		height:100vh;
	}
	.page{
		height:100%;
	}
	.tips{
		position:fixed;
		z-index: 1;
	}
	  .status_bar {
	        height: var(--status-bar-height);
	        width: 100%;
			position:fixed;
			z-index:1;
	    }
	.login_ima{
		width:120rpx;
		height:120rpx;
		position:fixed;
		top:70rpx;
		left:60rpx;
	}
	.show_btn{
		background-color: white;
		position:absolute;
		font-size: 20rpx;
		top:60rpx;
		left:25rpx;
		opacity:0.5;
		border-radius: 40%;
	
	}
	.btn_ima{
		width:100%;
		margin-top:5%;
		
	}
	.page{
		height:100%;
		position: relative;
	}
	.btns{
		position: fixed;
		z-index:1;
		width:50%;
		bottom:15%;
		left:3%;
		
		height:auto;
	}
	.btn{
		width:100%;
		background:rgba(0,0,0,0);
		margin:-10% auto ;
		padding:0;
		border-color:rgba(0,0,0,0);
	}
	.ima{
		border-color:rgba(0,0,0,0);
		position: absolute;
		z-index: -1;
		height:100vh;
		width:100%;
	}
</style> 