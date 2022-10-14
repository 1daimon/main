'use strict';
const db=uniCloud.database();
const userdb=db.collection('user');
exports.main = async (event, context) => {
	//event为客户端上传的参数
	console.log(event)
	const param =JSON.parse(event.body)
	let {data:[user]}=await userdb.where({
		username:param.username,
		password:param.password
	}).get();
	console.log(user)
	//返回数据给客户端
	
		return user;
	
};
