const videoElement = document.getElementsByClassName('input-video')[0];
const canvasElement = document.getElementsByClassName('output-canvas')[0];
const canvasCtx = canvasElement.getContext('2d');
let lastPoseData = null;

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

// 랜드마크와 스켈레톤을 그리는 함수
function onResults(results) {
    canvasCtx.save();
    canvasCtx.clearRect(0, 0, canvasElement.width, canvasElement.height);
    canvasCtx.drawImage(results.image, 0, 0, canvasElement.width, canvasElement.height);
  
    if (results.poseLandmarks) {
        // 랜드마크를 콘솔에 출력
        console.log(results.poseLandmarks);
        lastPoseData = results.poseLandmarks;

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
}

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

// JSON 형식으로 데이터 저장
function savePoseDataToFile() {
    if (lastPoseData) {
        const jsonData = JSON.stringify(lastPoseData.map(landmark => ({
            x: landmark.x,
            y: landmark.y,
            z: landmark.z
        })));

        fetch('http://localhost:8081/api/savePoseData', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: jsonData
        })
        .then(response => response.json())
        .then(data => console.log('Success:', data))
        .catch((error) => console.error('Error:', error));
    }
}

// 버튼에 이벤트 리스너 추가
document.getElementById('saveDataButton').addEventListener('click', savePoseDataToFile);
