<template>
	<div>
		<image  @click="back" class="home" src="https://pic1.imgdb.cn/item/633976c516f2c2beb129b2b0.png" mode=""></image>
		<image  class="back_ima" src="https://pic1.imgdb.cn/item/63491bcb16f2c2beb1ccc970.jpg" mode="widthFix"></image>
	</div>
</template>

<script>
	export default{
		data(){
			return {
				
			}
		},
		methods:{
			back(){
				uni.navigateTo({
					url:'/pages/index/index'
				})
			}
		}
	}
</script>

<style scoped>
	.home{
		width:90rpx;
		position:absolute;
		height:90rpx;
		top:1%;
		z-index:1;
		left:0%;
	}
	.back_ima{
		width:100%;
		position: absolute;
		z-index:-1;
	}
</style>