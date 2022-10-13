'use strict';

	//event为客户端上传的参数
	const db=uniCloud.database();
	const userdb=db.collection('user');
	exports.main=async(event,context)=>{
		let adduserres=await userdb.add(event);
		console.log('event : ', event)
		
		//返回数据给客户端
		return adduserres
	
	
};
