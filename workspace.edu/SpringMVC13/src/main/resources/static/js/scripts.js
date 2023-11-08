const videoElement = document.getElementsByClassName('input-video')[0];
const canvasElement = document.getElementsByClassName('output-canvas')[0];
const canvasCtx = canvasElement.getContext('2d');

// MediaPipe Pose 라이브러리 설정
const pose = new Pose({
  locateFile: (file) => `https://cdn.jsdelivr.net/npm/@mediapipe/pose/${file}`
});

pose.setOptions({
  upperBodyOnly: true,
  modelComplexity: 1,
  smoothLandmarks: true,
  enableSegmentation: false,
  smoothSegmentation: false,
  minDetectionConfidence: 0.5,
  minTrackingConfidence: 0.5
});

// 결과 처리 콜백
pose.onResults(onResults);

// 카메라 설정
const camera = new Camera(videoElement, {
  onFrame: async () => {
    await pose.send({ image: videoElement });
  },
  width: 640,
  height: 480
});

camera.start();

// WebSocket 연결 추가
const ws = new WebSocket('ws://localhost:8081/pose');

ws.onopen = function() {
    console.log('Connected to the WebSocket server.');
};

ws.onerror = function(event) {
    console.error("WebSocket error observed:", event);
};

function sendPoseDataToServer(poseData) {
    if (ws.readyState === WebSocket.OPEN) {
        ws.send(JSON.stringify(poseData));
    }
}





// 랜드마크와 스켈레톤을 그리는 함수
function onResults(results) {
  canvasCtx.save();
  canvasCtx.clearRect(0, 0, canvasElement.width, canvasElement.height);
  canvasCtx.drawImage(results.image, 0, 0, canvasElement.width, canvasElement.height);
  
  if (results.poseLandmarks) {
    // 랜드마크를 콘솔에 출력
    console.log(results.poseLandmarks);

    // 스켈레톤을 그림
    drawConnectors(canvasCtx, results.poseLandmarks, POSE_CONNECTIONS, {
      color: '#ffffff',
      lineWidth: 3
    });
    drawLandmarks(canvasCtx, results.poseLandmarks, {
      color: '#FF0000',
      lineWidth: 2
    });
  }
  canvasCtx.restore();
	
// websocket 추가
	if (results.poseLandmarks) {
        sendPoseDataToServer(results.poseLandmarks);
    }
}

// MediaPipe 라이브러리의 유틸리티 함수를 사용하여 랜드마크와 연결선을 그림
function drawLandmarks(context, landmarks, {color, lineWidth}) {
  for (const landmark of landmarks) {
    context.beginPath();
    context.arc(landmark.x * canvasElement.width, landmark.y * canvasElement.height, lineWidth, 0, 2 * Math.PI);
    context.fillStyle = color;
    context.fill();
  }
}

function drawConnectors(context, landmarks, connections, {color, lineWidth}) {
  for (const [startIdx, endIdx] of connections) {
    const startLandmark = landmarks[startIdx];
    const endLandmark = landmarks[endIdx];
    if (startLandmark && endLandmark) {
      context.beginPath();
      context.moveTo(startLandmark.x * canvasElement.width, startLandmark.y * canvasElement.height);
      context.lineTo(endLandmark.x * canvasElement.width, endLandmark.y * canvasElement.height);
      context.strokeStyle = color;
      context.lineWidth = lineWidth;
      context.stroke();
    }
  }
}
