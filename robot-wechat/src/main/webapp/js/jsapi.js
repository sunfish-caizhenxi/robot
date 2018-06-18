//判断当前客户端版本是否支持指定JS接口
function checkJsApi(){
	wx.checkJsApi({
	    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
	    success: function(res) {
	    // 以键值对的形式返回，可用的api值true，不可用为false
	    // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	    }
	});
};
//获取“分享到朋友圈”按钮点击状态及自定义分享内容接口（即将废弃）
function onMenuShareTimeline(){
	wx.onMenuShareTimeline({
	    title: '', // 分享标题
	    link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
	    imgUrl: '', // 分享图标
	    success: function () {
	    // 用户点击了分享后执行的回调函数
	}
	});
};
//获取“分享给朋友”按钮点击状态及自定义分享内容接口（即将废弃）
function onMenuShareAppMessage(){
	wx.onMenuShareAppMessage({
		title: '', // 分享标题
		desc: '', // 分享描述
		link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		imgUrl: '', // 分享图标
		type: '', // 分享类型,music、video或link，不填默认为link
		dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		success: function () {
		// 用户点击了分享后执行的回调函数
		}
		});
};
//获取“分享到QQ”按钮点击状态及自定义分享内容接口
function onMenuShareQQ(){
	wx.onMenuShareQQ({
		title: '', // 分享标题
		desc: '', // 分享描述
		link: '', // 分享链接
		imgUrl: '', // 分享图标
		success: function () {
		// 用户确认分享后执行的回调函数
		},
		cancel: function () {
		// 用户取消分享后执行的回调函数
		}
		});
};
//获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
function onMenuShareWeibo(){
	wx.onMenuShareWeibo({
		title: '', // 分享标题
		desc: '', // 分享描述
		link: '', // 分享链接
		imgUrl: '', // 分享图标
		success: function () {
		// 用户确认分享后执行的回调函数
		},
		cancel: function () {
		// 用户取消分享后执行的回调函数
		}
		});
};
//获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
function onMenuShareQZone(){
	wx.onMenuShareQZone({
		title: '', // 分享标题
		desc: '', // 分享描述
		link: '', // 分享链接
		imgUrl: '', // 分享图标
		success: function () {
		// 用户确认分享后执行的回调函数
		},
		cancel: function () {
		// 用户取消分享后执行的回调函数
		}
		});
};
//拍照或从手机相册中选图接口
function chooseImage(){
	wx.chooseImage({
		count: 1, // 默认9
		sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
		sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
		success: function (res) {
		var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		}
		});
};
//预览图片接口
function previewImage(){
	wx.previewImage({
		current: '', // 当前显示图片的http链接
		urls: [] // 需要预览的图片http链接列表
		});
};
//上传图片接口
function uploadImage(){
	wx.uploadImage({
		localId: '', // 需要上传的图片的本地ID，由chooseImage接口获得
		isShowProgressTips: 1, // 默认为1，显示进度提示
		success: function (res) {
		var serverId = res.serverId; // 返回图片的服务器端ID
		}
		});
};
//下载图片接口
function downloadImage(){
	wx.downloadImage({
		serverId: '', // 需要下载的图片的服务器端ID，由uploadImage接口获得
		isShowProgressTips: 1, // 默认为1，显示进度提示
		success: function (res) {
		var localId = res.localId; // 返回图片下载后的本地ID
		}
		});
};
//获取本地图片接口
function getLocalImgData(){
	wx.getLocalImgData({
		localId: '', // 图片的localID
		success: function (res) {
		var localData = res.localData; // localData是图片的base64数据，可以用img标签显示
		}
		});
};
//开始录音接口
function startRecord(){
	wx.startRecord();
};
//停止录音接口
function stopRecord(){
	wx.stopRecord({
		success: function (res) {
		var localId = res.localId;
		}
		});
};
//监听录音自动停止接口
function onVoiceRecordEnd(){
	wx.onVoiceRecordEnd({
		// 录音时间超过一分钟没有停止的时候会执行 complete 回调
		complete: function (res) {
		var localId = res.localId;
		}
		});
};
//播放语音接口
function playVoice(){
	wx.playVoice({
		localId: '' // 需要播放的音频的本地ID，由stopRecord接口获得
		});
};
//暂停播放接口
function pauseVoice(){
	wx.pauseVoice({
		localId: '' // 需要暂停的音频的本地ID，由stopRecord接口获得
		});
};
//停止播放接口
function stopVoice(){
	wx.stopVoice({
		localId: '' // 需要停止的音频的本地ID，由stopRecord接口获得
		});
};
//监听语音播放完毕接口
function onVoicePlayEnd(){
	wx.onVoicePlayEnd({
		success: function (res) {
		var localId = res.localId; // 返回音频的本地ID
		}
		});
};
//上传语音接口
function onVoicePlayEnd(){
	wx.uploadVoice({
		localId: '', // 需要上传的音频的本地ID，由stopRecord接口获得
		isShowProgressTips: 1, // 默认为1，显示进度提示
		success: function (res) {
		var serverId = res.serverId; // 返回音频的服务器端ID
		}
		});
};
//下载语音接口
function downloadVoice(){
	wx.downloadVoice({
		serverId: '', // 需要下载的音频的服务器端ID，由uploadVoice接口获得
		isShowProgressTips: 1, // 默认为1，显示进度提示
		success: function (res) {
		var localId = res.localId; // 返回音频的本地ID
		}
		});
};
//识别音频并返回识别结果接口
function translateVoice(){
	wx.translateVoice({
		localId: '', // 需要识别的音频的本地Id，由录音相关接口获得
		isShowProgressTips: 1, // 默认为1，显示进度提示
		success: function (res) {
		alert(res.translateResult); // 语音识别的结果
		}
		});
};
//获取网络状态接口
function getNetworkType(){
	wx.getNetworkType({
		success: function (res) {
		var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
		}
		});
};
//使用微信内置地图查看位置接口
function openLocation(){
	wx.openLocation({
		latitude: 0, // 纬度，浮点数，范围为90 ~ -90
		longitude: 0, // 经度，浮点数，范围为180 ~ -180。
		name: '', // 位置名
		address: '', // 地址详情说明
		scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大
		infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
		});
};
//获取地理位置接口
function getLocation(){
	wx.getLocation({
		type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
		success: function (res) {
		var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
		var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
		var speed = res.speed; // 速度，以米/每秒计
		var accuracy = res.accuracy; // 位置精度
		}
		});
};
//开启查找周边ibeacon设备接口
function startSearchBeacons(){
	wx.startSearchBeacons({
		ticket:"",  //摇周边的业务ticket, 系统自动添加在摇出来的页面链接后面
		complete:function(argv){
		//开启查找完成后的回调函数
		}
		});
};
//关闭查找周边ibeacon设备接口
function stopSearchBeacons(){
	wx.stopSearchBeacons({
		complete:function(res){
		//关闭查找完成后的回调函数
		}
		});
};
//监听周边ibeacon设备接口
function onSearchBeacons(){
	wx.onSearchBeacons({
		complete:function(argv){
		//回调函数，可以数组形式取得该商家注册的在周边的相关设备列表
		}
		});
};
//关闭当前网页窗口接口
function closeWindow(){
	wx.closeWindow();
};
//批量隐藏功能按钮接口
function hideMenuItems(){
	wx.hideMenuItems({
		menuList: [] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
		});
};
//批量显示功能按钮接口
function showMenuItems(){
	wx.showMenuItems({
		menuList: [] // 要显示的菜单项，所有menu项见附录3
		});
};
//隐藏所有非基础按钮接口
function hideAllNonBaseMenuItem(){
	wx.hideAllNonBaseMenuItem();
	// “基本类”按钮详见附录3
};
//显示所有功能按钮接口
function showAllNonBaseMenuItem(){
	wx.showAllNonBaseMenuItem();
	// “基本类”按钮详见附录3
};
//调起微信扫一扫接口
function scanQRCode(){
	wx.scanQRCode({
		needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		success: function (res) {
		var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		}
		});
};
//跳转微信商品页接口
function openProductSpecificView(){
	wx.openProductSpecificView({
		productId: '', // 商品id
		viewType: '' // 0.默认值，普通商品详情页1.扫一扫商品详情页2.小店商品详情页
		});
};
//拉取适用卡券列表并获取用户选择信息
function chooseCard(){
	wx.chooseCard({
		shopId: '', // 门店Id
		cardType: '', // 卡券类型
		cardId: '', // 卡券Id
		timestamp: 0, // 卡券签名时间戳
		nonceStr: '', // 卡券签名随机串
		signType: '', // 签名方式，默认'SHA1'
		cardSign: '', // 卡券签名
		success: function (res) {
		var cardList= res.cardList; // 用户选中的卡券列表信息
		}
		});
};
//批量添加卡券接口
function addCard(){
	wx.addCard({
		cardList: [{
		cardId: '',
		cardExt: ''
		}], // 需要添加的卡券列表
		success: function (res) {
		var cardList = res.cardList; // 添加的卡券列表信息
		}
		});
};
//查看微信卡包中的卡券接口
function openCard(){
	wx.openCard({
		cardList: [{
		cardId: '',
		code: ''
		}]// 需要打开的卡券列表
		});
};
//发起一个微信支付请求
function chooseWXPay(){
	wx.chooseWXPay({
		timestamp: 0, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
		nonceStr: '', // 支付签名随机串，不长于 32 位
		package: '', // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=\*\*\*）
		signType: '', // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
		paySign: '', // 支付签名
		success: function (res) {
		// 支付成功后的回调函数
		}
		});
};
//共享收货地址接口
function openAddress(){
	wx.openAddress({
		success: function (res) {
		var userName = res.userName; // 收货人姓名
		var postalCode = res.postalCode; // 邮编
		var provinceName = res.provinceName; // 国标收货地址第一级地址（省）
		var cityName = res.cityName; // 国标收货地址第二级地址（市）
		var countryName = res.countryName; // 国标收货地址第三级地址（国家）
		var detailInfo = res.detailInfo; // 详细收货地址信息
		var nationalCode = res.nationalCode; // 收货地址国家码
		var telNumber = res.telNumber; // 收货人手机号码
		}
		});
};