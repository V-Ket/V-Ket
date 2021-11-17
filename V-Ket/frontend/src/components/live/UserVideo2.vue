<template>
<div v-if="streamManager" id="video-box">
	<ov-video :stream-manager="streamManager" id="videosize"/>
</div>
</template>

<style scoped>
#videosize{
	width: 16vw;
	border-radius: 20px;
	border: 3px solid black;
}
#video-box {
	padding: 0px;
	margin: 0px;
}

#video-info {
	position: absolute;
	top: 0px;
	left: 0%;
	padding-top: 1px;
	padding-bottom: 1px;
	padding-left: 13px;
	padding-right: 13px;
	background: #eee;
	border-end-end-radius: 5px;
}
</style>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>
