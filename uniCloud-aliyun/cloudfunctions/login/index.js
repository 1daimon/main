'use strict';
const db=uniCloud.database();
const userdb=db.collection('user');
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log(event)
	let {data:[user]}=await userdb.where({
		username:event.username,
		password:event.password
	}).get();
	console.log(user)
	//返回数据给客户端
	
		return user;
	
};
