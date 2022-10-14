<template>
	<div class="page">
		<div class="select" v-show="!readyselect">
			<uni-section title="难度选择" type="line">
			<uni-data-checkbox class="select_list" mode="button" v-model="radio1" :localdata="moding"></uni-data-checkbox>
			<button @click="decide_dif" class="decide_moding">确定</button>
			</uni-section>

		</div>
		
		<image @click="back" class="home" src="https://pic1.imgdb.cn/item/633976c516f2c2beb129b2b0.png" mode=""></image>
		<image class="back_ima" src="https://pic1.imgdb.cn/item/63393f8f16f2c2beb1e11bff.png" mode="scaleToFill"></image>
		<div class="chessBoard1">
			<div class="block" v-for="(item,index) in sum1" >	
				<image class="block_ima"  :src="ima_list1[index]" v-if="isShow"></image>
			</div>
		</div>
		<div class="chessBoard2">
			<div class="block" v-for="(item,index) in sum2" :key="index"  @click="step_in2(index)">
				<image class="block_ima"  :src="ima_list2[index]" v-if="isShow"></image>
			</div>
		</div>
		<div class="user1">
			<image :class="{'tou1':!start1,'tou1_start':start1}" :src="result_ima1" ></image>
			<div class="person_msg1">
			<div class="user_name1">人工智障</div>
			<image class="person1"  src="https://pic1.imgdb.cn/item/6338622e16f2c2beb125316b.png" mode=""></image>
			</div>
			
		</div>
		<uni-popup ref="alertDialog4" type="dialog">
						<uni-popup-dialog :type="msgType" cancelText="关闭" confirmText="同意" title="通知" content="这一格已经有骰子" @confirm="dialogConfirm"
							@close="dialogClose"></uni-popup-dialog>
					</uni-popup>
		<uni-popup ref="alertDialog1" type="dialog">
						<uni-popup-dialog :type="msgType" cancelText="关闭" confirmText="同意" title="通知" content="请选手正确完成操作" @confirm="dialogConfirm"
							@close="dialogClose"></uni-popup-dialog>
					</uni-popup>
		<uni-popup ref="alertDialog2" type="dialog">
						<uni-popup-dialog :type="msgType" cancelText="关闭" confirmText="同意" title="通知" content="请选择投掷格子" @confirm="dialogConfirm"
							@close="dialogClose"></uni-popup-dialog>
					</uni-popup>
		<uni-popup ref="alertDialog3" type="dialog">
									<uni-popup-dialog :type="msgType" cancelText="关闭" confirmText="同意" title="通知" content="请交换操作双方" @confirm="dialogConfirm"
										@close="dialogClose"></uni-popup-dialog>
					</uni-popup>
		<uni-popup ref="alertDialog5" type="dialog">
									<uni-popup-dialog :type="msgType" cancelText="返回首页" confirmText="再来一局" title="通知" :content="message" @confirm="dialogConfirm1"
										@close="dialogClose1"></uni-popup-dialog>
					</uni-popup>			
		<div class="user2">
			<div class="person_msg2">
				<image class="person2" src="https://pic1.imgdb.cn/item/6338622e16f2c2beb125316b.png" mode=""></image>
			<div class="user_name2">user2</div>
		</div>
		<image @click="work2" :class="{'tou2':!start2,'tou2_start':start2}" :src="result_ima2" ></image>
	</div>
	</div>
	 
</template>

<script>
	export default{
		data(){
			return{
				readyselect:false,
				moding:[
					{
						text: '简单',
						value: 0
					},
					{
						text: '中等',
						value: 1
					},
					{
						text: '困难',
						value: 2
					},
				],
				
				radio1:'',
				message:'',
				msgType: '',
				sum1:[0,0,0,0,0,0,0,0,0],
				sum2:[0,0,0,0,0,0,0,0,0],
				start1:false,
				msgType:'',
				full:0,
				start2:false,
				please:0,
				use1:0,
				use2:0,
				score1:0,
				result_text:'',
				score2:0,
				ready1:1,
				winner:'',
				ready2:1,
				result_ima1:"https://pic1.imgdb.cn/item/633845fb16f2c2beb1030b47.png",
				result_ima2:"https://pic1.imgdb.cn/item/633845fb16f2c2beb1030b47.png",
				result_num:'',
				ima_list1:[
					'','','','','',''
				],
				ima_list2:[
					'','','','','',''
				],
				array:[
					"https://pic1.imgdb.cn/item/6339338716f2c2beb1d379cb.gif",
					"https://pic1.imgdb.cn/item/6339338716f2c2beb1d379d1.gif",
					"https://pic1.imgdb.cn/item/6339338716f2c2beb1d379d9.gif",
					"https://pic1.imgdb.cn/item/6339338716f2c2beb1d379e2.gif",
					"https://pic1.imgdb.cn/item/6339338716f2c2beb1d379ea.gif",
					"https://pic1.imgdb.cn/item/6339339316f2c2beb1d3860b.gif"
				],
				array_t:[
					'https://pic1.imgdb.cn/item/633845bf16f2c2beb102c412.png',
					'https://pic1.imgdb.cn/item/633845bf16f2c2beb102c408.png',
					'https://pic1.imgdb.cn/item/633845fb16f2c2beb1030b4a.png',
					'https://pic1.imgdb.cn/item/633845bf16f2c2beb102c3fe.png',
					'https://pic1.imgdb.cn/item/633845bf16f2c2beb102c3f7.png',
					'https://pic1.imgdb.cn/item/633845fb16f2c2beb1030b47.png'
				],
				click:0,
				changenum1:0,
				changenum2:0,
				isShow:true
			}
		},
		methods:{
			decide_dif(){
				this.readyselect=true
				console.log(this.radio1)
				this.message=''
				this.msgType=''
				this.sum1=[0,0,0,0,0,0,0,0,0]
				this.sum2=[0,0,0,0,0,0,0,0,0]
				this.start1=false
				this.msgType=''
				this.full=0
				this.start2=false
				this.please=0
				this.use1=0
				this.use2=0
				this.score1=0
				this.result_text=''
				this.score2=0
				this.ready1=1
				this.winner=''
				this.ready2=1,
				this.result_ima1="https://pic1.imgdb.cn/item/633845fb16f2c2beb1030b47.png"
				this.result_ima2="https://pic1.imgdb.cn/item/633845fb16f2c2beb1030b47.png"
				this.result_num=''
				this.ima_list1=[
					'','','','','',''
				]
				this.ima_list2=[
					'','','','','',''
				]
				this.click=0
				this.changenum1=0
				this.changenum2=0
				
				this.isShow= false// 更新dom
				this.$nextTick(()=>{
				this.isShow = true
				    })
			},
			easyMod(sum1,sum2,figure){
				var Block=[3,3,3]
				
				for(var i=0;i<9;i++){
					if(sum1[i]!=0){
						if(i>=0&&i<=2) {
							 Block[0]=Block[0]-1
							 }
						 else if(i>=3&&i<=5){
							 Block[1]=Block[1]-1
						 } 
						else if(i>=6&&i<=8){
							 Block[2]=Block[2]-1
						}
					}
				}
				var flag=0;
				   var blnum=0;
				    for(var i=0;i<3;i++){
				        if(Block[i]>blnum){
				            flag=i
				            blnum=Block[i]
				        }
				    }
				var nextStep=0;
				 switch (flag)
				    {
				    case 0:
				        for(var i=0;i<3;i++){
				            if(sum1[i]==0){
								 nextStep=i
							}
				        }
				        break;
				    case 1:
				        for(var i=3;i<6;i++){
				            if(sum2[i]==0){
								nextStep=i
							} 
				        }
				        break;
				    case 2:
				        for(var i=6;i<9;i++){
				            if(sum1[i]==0) {
								nextStep=i
							}
				        }
				        break;
				    default:
				        break;
				    }
				    return nextStep
				
			},
			stableMod(sum1,sum2,figure){
				var ownscore=this.calculate(sum1)
				var otherscore=this.calculate(sum2)
				var d_value=[0,0,0,0,0,0,0,0,0]
				
				
				for(var i=0;i<9;i++){
					if(sum1[i]==0){
						var own1=[0,0,0,0,0,0,0,0,0]
						for(var li=0;li<9;li++){
							own1[li]=sum1[li]
						}
					
						var other1=[0,0,0,0,0,0,0,0,0]
						for(var ii=0;ii<9;ii++) {
						other1[ii]=sum2[ii];//复制一个otherboard
						  //cout<<other1[ii]<<" ";
						}
						
						own1[i]=figure;
						
						 if(i>=0&&i<=2){//同行消灭处理
						    for(var j=0;j<=2;j++){
						     if(other1[j]==figure) {
								 other1[j]=0;
							 }
						     }
						     }
						     else if(i>=3&&i<=5){
						        for(var j=3;j<=5;j++){
						           if(other1[j]==figure) {
									   other1[j]=0;
								   }
						            }
						            }
						    else if(i>=6&&i<=8){
						        for(var j=6;j<=8;j++){
						             if(other1[j]==figure) {
										 other1[j]=0;
									 }
						            }
						            }
						
						     var D_ownscore=this.calculate(own1)-ownscore
						     var D_otherscore=otherscore-this.calculate(other1)
						     var Differ=D_otherscore+D_ownscore
						    d_value[i]=Differ
						
					}
					
				}
				var max=0,flag=0
				for(var i=0;i<9;i++){
					if(d_value[i]>max){
						max=d_value[i];
						flag=i
					}
				}
				console.log("flag",flag)
				return flag;
			},
			nextStep(sum1,sum2,figure){
				 var Block=[3,3,3];
				 
				for(var i=0;i<9;i++){//获取格子填充情况
				    if(sum1[i]!=0){
				        if(i>=0&&i<=2) {
							Block[0]--;
						}
				        else if(i>=3&&i<=5) {
							Block[1]--;
						}
				        else if(i>=6&&i<=8) {
							Block[2]--;
						}
				        }
				    }//
					var ownScore=this.calculate(sum1)
					var otherScore=this.calculate(sum2)
					
					var D_value=[0,0,0,0,0,0,0,0,0]
					  for(var i=0;i<9;i++){
					        if(sum1[i]==0) {
					           var own1=[];
					            for(var ii=0;ii<9;ii++) {
					                own1[ii]=sum1[ii];
					                
					            }
					          
					            //复制一个ownboard
					            var other1=[];
					            for(var ii=0;ii<9;ii++) {
					                other1[ii]=sum2[ii];//复制一个otherboard
					              
					            }
					           
					
					            //对数组进行操作
					            own1[i]=figure;
					            if(i>=0&&i<=2){//同行消灭处理
					                for(var j=0;j<=2;j++){
					                    if(other1[j]==figure) {
											other1[j]=0;
										}
					                }
					            }
					            else if(i>=3&&i<=5){
					                for(var j=3;j<=5;j++){
					                    if(other1[j]==figure){
											 other1[j]=0;
										}
					                }
					            }
					            else if(i>=6&&i<=8){
					                for(var j=6;j<=8;j++){
					                    if(other1[j]==figure) {
											other1[j]=0;
										}
					                }
					            }
					
					            var D_ownscore=this.calculate(own1)-this.calculate(sum1);
					            var D_otherscore=this.calculate(sum2)-this.calculate(other1);
					            var Differ=D_otherscore+D_ownscore;//获取操作后的差值
					            D_value[i]=Differ;
					
					        }
					    }//至此D_value的值已经初步形成
						
						 for(var i=0;i<3;i++){//根据已知格子权重赋值
						        if(Block[i]!=0)
								{
						        switch (i)
						        {
						        case 0:
						            for(var  j=0;j<3;j++){
						                if(D_value[j]!=0) {
										D_value[j]=D_value[j]+Block[0]*2.5;
						                if(Block[0]==3) {
											D_value[j];
										}
						                if(Block[0]==2) {
											D_value[j]=D_value[j]-0;
										}
						                if(Block[0]==1){
											 D_value[j]=D_value[j]-3;
										}
						                if(D_value[j]<0){
											 D_value[j]=0.1;
										}
						                }
						            }
						            break;
						        case 1:
						            for(var j=3;j<6;j++){
						                if(D_value[j]!=0) {
											D_value[j]=D_value[j]+Block[1]*2.5;
						                if(Block[1]==3) {
											D_value[j];
										}
						                if(Block[1]==2) {
											D_value[j]=D_value[j]-0;
										}
						                if(Block[1]==1){
											 D_value[j]=D_value[j]-3;
										}
						                if(D_value[j]<0){
											D_value[j]=0.1;
										} 
						                }
						            }
						            break;
						        case 2:
						            for(var j=6;j<9;j++){
						                if(D_value[j]!=0) {
											D_value[j]=D_value[j]+Block[2]*2.5;
						                if(Block[2]==3) {
											D_value[j];
										}
						                if(Block[2]==2) {
											D_value[j]=D_value[j]-0;
										}
						                if(Block[2]==1) {
											D_value[j]=D_value[j]-3;
										}
						                if(D_value[j]<0) {
											D_value[j]=0.1;
										}
						                }
						            }
						            break;
						        default:
						            break;
						        }
								}
						    }
					var max = 0;
				    var Di_value=[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0];
				    var flag=0;
				    for(var i=0;i<9;i++) {
				
				        if(D_value[i]>max){
				            max=D_value[i];
				            flag=i;
				        }
				    }
				    return flag;	
			},
		
			back(){
				uni.navigateTo({
					url:'/pages/index/index'
				})
			},
			check(sum){
				for(var i=0;i<9;i++){
					if(sum[i]==0){
						break;
					}
				}
				if(i>=9){
					return 1;
				}else{
					return 0;
				}
			},
			calculate(nums){
				var count=0
				var sum1=[0,0,0,0,0,0,0]
				var sum2=[0,0,0,0,0,0,0]
				var sum3=[0,0,0,0,0,0,0]
				for(var i=0;i<9;i++){
					if(i>=0&&i<3){
					sum1[nums[i]]++	
					}else if(i>=3&&i<6){
						sum2[nums[i]]++	
					}else{
						sum3[nums[i]]++	
					}
					}
				console.log(sum1,sum2,sum3)
				for(var j=0;j<3;j++){
					
					for (var i=1;i<=6;i++){
						if(j==0){
							count=count+i*sum1[i]*sum1[i]
						}else if(j==1){
							count=count+i*sum2[i]*sum2[i]
						}else{
							count=count+i*sum3[i]*sum3[i]
						}
						
					}
				}
				return count;
			},
			dialogToggle5(type) {
				this.msgType = type
				this.$refs.alertDialog5.open()
			},
			dialogToggle4(type) {
				this.msgType = type
				this.$refs.alertDialog4.open()
			},
			dialogToggle3(type) {
				this.msgType = type
				this.$refs.alertDialog3.open()
			},
			dialogToggle2(type) {
				this.msgType = type
				this.$refs.alertDialog2.open()
			},
			dialogToggle1(type) {
				this.msgType = type
				this.$refs.alertDialog1.open()
			},
			step_in1(index){
				if(this.please==0){
					this.dialogToggle1('info')
					return
				}
				if(this.click==1){
					this.dialogToggle3('info')
					return
				}
				if(this.use2==1){
					this.dialogToggle1('info')
					return
				}
				if(this.sum1[index]!=0){
					this.dialogToggle4('info')
					return
				}
				this.changenum1=1
				this.ready1=1
				this.click=1
				this.ready2=true			
				console.log("下在",index)
				this.sum1[index]=this.result_num
				this.ima_list1[index]=this.array_t[this.result_num-1]
				console.log(this.ima_list1[index])
				this.ready=true
				this.isShow= false// 更新dom
				this.$nextTick(()=>{
				this.isShow = true
				    })
				this.use1=0
				this.click=1
				this.full=this.check(this.sum1)
			
				if(0<=index &&index<3){
					for (var i=0;i<3;i++){
						if(this.sum2[i]==this.sum1[index]){
							this.sum2[i]=0
							this.ima_list2[i]=''
						}
					}
						this.isShow= false// 更新dom
						this.$nextTick(()=>{
						this.isShow = true
						    })
				}else if(3<=index &&index<6){
					for (var i=3;i<6;i++){
						if(this.sum2[i]==this.sum1[index]){
							this.sum2[i]=0
							this.ima_list2[i]=''
						}
					}
					this.isShow= false// 更新dom
					this.$nextTick(()=>{
					this.isShow = true
					    })
				}else{
					for (var i=6;i<9;i++){
						if(this.sum2[i]==this.sum1[index]){
							this.sum2[i]=0
							this.ima_list2[i]=''
						}
					}
					this.isShow= false// 更新dom
					this.$nextTick(()=>{
					this.isShow = true
					    })
				}
				if(this.full==1){
					console.log(this.sum1,this.sum2)
					this.score1=this.calculate(this.sum1)
					this.score2=this.calculate(this.sum2)
					if(this.score1>this.score2){
						this.winner='人工智障'
					}else{
						this.winner='user2'
					}
				
					this.message = `用户分数  人工智障:${this.score1},user2:${this.score2},胜者:${this.winner}`
					this.dialogToggle5('info')
					return 
				}
			},
			step_in2(index){
				if(this.please==0){
					this.dialogToggle1('info')
					return
				}
				if(this.click==1){
					this.dialogToggle3('info')
					return
				}
				if(this.use1==1){
					this.dialogToggle1('info')
					return
				} 
				if(this.sum2[index]!=0){
					this.dialogToggle4('info')
					return
				}
				this.changenum2=1
				this.ready2=1
				this.click=1
				console.log(index)
				this.ready1=true
				this.sum2[index]=this.result_num
				this.ima_list2[index]=this.array_t[this.result_num-1]
				console.log(this.ima_list2[index])
				this.isShow= false// 更新dom
				this.$nextTick(()=>{
				this.isShow = true
				    })
				this.use2=0
				this.click=1
				this.full=this.check(this.sum2)
				
				if(0<=index &&index<3){
					for (var i=0;i<3;i++){
						if(this.sum1[i]==this.sum2[index]){
							this.sum1[i]=0
							this.ima_list1[i]=''
						}
					}
						this.isShow= false// 更新dom
						this.$nextTick(()=>{
						this.isShow = true
						    })
				}else if(3<=index &&index<6){
					for (var i=3;i<6;i++){
						if(this.sum1[i]==this.sum2[index]){
							this.sum1[i]=0
							this.ima_list1[i]=''
						}
					}
					this.isShow= false// 更新dom
					this.$nextTick(()=>{
					this.isShow = true
					    })
				}else{
					for (var i=6;i<9;i++){
						if(this.sum1[i]==this.sum2[index]){
							this.sum1[i]=0
							this.ima_list1[i]=''
						}
					}
					this.isShow= false// 更新dom
					this.$nextTick(()=>{
					this.isShow = true
					    })
				}
			if(this.full==1){
				this.score1=this.calculate(this.sum1)
				this.score2=this.calculate(this.sum2)
				if(this.score1>this.score2){
					this.winner='人工智障'
				}else{
					this.winner='user2'
				}
				this.message = `用户分数:  人工智障:${this.score1} ; user2:${this.score2} ; 胜者 ${this.winner}`
				this.dialogToggle5('info')
				return
			}
			
			var that=this
			var t=setTimeout(function(){
				that.work1()
			},1000);
		
			
				
			},
			back(){
				uni.navigateTo({
					url:'/pages/index/index'
				})
			},
			dialogClose() {
				console.log("nsuc")
			},
			dialogClose1() {
				uni.navigateTo({
					url:'/pages/index/index'
				})
			},
			dialogConfirm1() {
							console.log('点击确认')
							this.messageText = `点击确认了 ${this.msgType} 窗口`
							this.readyselect=false
						},
			
			work1(){
				if(this.use2==1){
					this.dialogToggle1('info')
					return
				}
				if(this.ready1==0){
					this.dialogToggle2('info')
					return
				}
				if(this.changenum1==1){
					this.dialogToggle3('info')
					return 
				}
				this.please=1
				this.changenum2=0
				this.use1=1
				this.click=0
				this.ready1=0
				this.start1=true
				this.result_num=Math.floor(Math.random() * 6) + 1;
				console.log("cas",this.result_num)
			
				this.result_ima1=this.array[this.result_num-1]
				var that=this
				console.log(this.radio1)
				var t=setTimeout(function(){
					var index
					if(that.radio1==0){
						index=that.easyMod(that.sum1,that.sum2,that.result_num)
					}else if(that.radio1==1){
						index=that.stableMod(that.sum1,that.sum2,that.result_num)
					}else{
						index=that.nextStep(that.sum1,that.sum2,that.result_num)
					}
					that.step_in1(index)
				},1000);
					
				 
					
				
				
			},
			work2(){
				if(this.use1==1){
					this.dialogToggle1('info')
					return
				}
				if(this.ready2==0){
					this.dialogToggle2('info')
					return 
				}
				if(this.changenum2==1){
					this.dialogToggle3('info')
					return
				}
				this.please=1
				this.changenum1=0
				this.use2=1
				this.ready2=0
				this.click=0
				this.start2=true
				this.result_num=Math.floor(Math.random() * 6) + 1;
				console.log(this.result_num)
				this.ready2=false
				if(this.result_num==1){
					this.result_ima2=this.array[0]
				}else if(this.result_num==2){
					this.result_ima2=this.array[1]
				}else if(this.result_num==3){
					this.result_ima2=this.array[2]
				}else if(this.result_num==4){
					this.result_ima2=this.array[3]
				}else if(this.result_num==5){
					this.result_ima2=this.array[4]
				}else if(this.result_num==6){
					this.result_ima2=this.array[5]
				}
		
					var t = setTimeout(function () {
					    this.result_ima2=this.array_t[this.result_num-1]
						console.log('加载框已关闭...')
						
					    }, 3000);
						
					clearTimeout(t)
				/*for(var i=0;i<1000000;i++){
					this.result_num=Math.floor(Math.random() * 6) + 1;
					if(this.result_num==1){
						this.one=this.one+1
					}else if(this.result_num==2){
						this.two=this.two+1
					}else if(this.result_num==3){
						this.three=this.three+1
					}else if(this.result_num==4){
						this.four=this.four+1
					}else if(this.result_num==5){
						this.five=this.five+1
					}else if(this.result_num==6){
						this.six=this.six+1
					}
				}
				console.log(this.one,this.two,this.three,this.four,this.five,this.six)
			}*/
		}
	},
	}
</script>

<style scoped>
	.select{
		position:fixed;
		z-index:3;
		left:18%;
		border-radius: 20rpx;
		top:30%;
	}
	.chessBoard1{
		position:absolute;
		background-color: rgba(255,255,255,0.5);
		z-index:1;
		width:58.64%;
		height: 27.32%;
		display: flex;
		top:20.3%;
		flex-wrap: wrap;
		margin-left:20.68%;
	}
	.chessBoard2{
		position:absolute;
		background-color: rgba(255,255,255,0.5);
		z-index:1;
		width:58.64%;
		height: 27.32%;
		display: flex;
		top:54.21%;
		flex-wrap: wrap;
		margin-left:20.68%;
	}
	.page{
		height:100vh;
	}
	.block{
		width:30%;
		height:30%;
		margin:auto;
	}
	
	.block_ima{
		width:90%;
		height:90%;
		margin:5%;
	}
	.back_ima{
		width:100%;
		height:100vh;
		position: absolute;
		z-index:-1;
	}
	.tou{
		margin-left:10%;
		width:90rpx;
		height:90rpx;
	}
	.select_list{
		width:80%;
		margin:10rpx auto;
	}
	.person1{
		width:80rpx;
		height:80rpx;
	}
	.person2{	
		width:80rpx;
		height:80rpx;
	}
	.decide_moding{
		width:80%;
		margin-left:10%;
		margin-top:5%;
		margin-bottom: 5%;
	}
	.user1{
		width:80%;
		margin:0 10%;
		top:10%;
		display: flex;
		position:fixed;
		justify-content:space-between;
	}
	.person_msg1{
		
		display: flex;
	}
	.person_msg2{
		display: flex;
		margin-left:10%;
		margin-top:15rpx;
	}
	.user_name1{
		font-size: 50rpx;
		margin-right: 20rpx;
	}
	.user_name2{
		font-size: 50rpx;
		margin-left: 20rpx;
		
	}
	.tou2{
		margin-left:25%;
		width:90rpx;
		height:90rpx;
		margin-top:10rpx;
	}
	.tou2_start{
		margin-left:25%;
		width:110rpx;
		height:110rpx;
	}
	.tou1{
		margin-left:10%;
		width:90rpx;
		height:90rpx;
		margin-top:10rpx;
		margin-right:20%;
	}
	.tou1_start{
		margin-left:5%;
		width:110rpx;
		height:110rpx;
	}
	.user2{
		position: fixed;
		bottom:8%;
		display: flex;
		width:80%;
		margin:0 10%;
		height:110rpx;
	}
	.home{
		height:90rpx;
		width:90rpx;
		position: fixed;
		left:8%;
		top:4%;
	}
	 
</style>