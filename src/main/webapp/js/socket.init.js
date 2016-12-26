var url = url || null, ws = ws || null, transports = transports || [];
var WS = {
	init : function(){
		if ('WebSocket' in window) {
			url = '/websocket';
	    } else if ('MozWebSocket' in window) {
	    	url = '/websocket';
	    }else{
	    	url = '/sockjs/websocket/info';
	    }
		WS.updateUrl();
	},
	updateUrl : function() {
		if (url.indexOf('sockjs') != -1) {
			url = 'http://' + window.location.host + url;
		} else {
			if (window.location.protocol == 'http:') {
				url = 'ws://' + window.location.host + url;
			} else {
				url = 'wss://' + window.location.host + url;
			}
		}
		WS.connect()
	},
	connect : function() {

		ws = (url.indexOf('sockjs') != -1) ? new SockJS(url, undefined, []) : new WebSocket(url);

		ws.onopen = function() {
			console.info('Info: connection opened.');
		};
		ws.onmessage = function(event) {
			var $msg = $('#Unread-msg');
			$msg.css('display','block').html($msg.html() ? Number($msg.html()) + 1 : 1);
		};
		ws.onclose = function(event) {
			alert('Info: connection closed.')
			console.info('Info: connection closed.');
			console.info(event);
		};
	},
	disconnect : function() {
		if (ws != null) {
			ws.close();
			ws = null;
		}
	},
	send : function (userId) {
		if (ws != null) {
			console.info('Sent: ' + userId);
			ws.send(userId);
		} else {
			WS.init();
			alert('connection not established, please connect.');
		}
	}
}