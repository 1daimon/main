'use strict';

	//event为客户端上传的参数
	const db=uniCloud.database();
	const userdb=db.collection('user');
	exports.main=async(event,context)=>{
		const param =JSON.parse(event.body)
		
		let adduserres=await userdb.add(param);
		console.log('event : ', event)
		
		//返回数据给客户端
		return adduserres
	
	
};
