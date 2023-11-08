const ws = new WebSocket('ws://localhost:8082/pose');

ws.onopen = function() {
    console.log('Connected to the WebSocket server.');
};

function sendPoseDataToServer(poseData) {
    if (ws.readyState === WebSocket.OPEN) {
        ws.send(JSON.stringify(poseData));
    }
}

function onResults(results) {
    // ... 기존의 onResults 함수 내용 ...
    if (results.poseLandmarks) {
        sendPoseDataToServer(results.poseLandmarks);
    }
}
