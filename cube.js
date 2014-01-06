var canvas = document.getElementById("canvas");
var w = canvas.width;
var h = canvas.height;
var scene = new THREE.Scene();
var camera = new THREE.OrthographicCamera(-0.75,
                                          0.75,
                                          0.9,
                                          -0.9,
                                          -200,
                                          200);

var renderer = new THREE.WebGLRenderer({canvas: canvas});
renderer.setSize(canvas.width, canvas.height);

var rotX = Math.PI/4;
var rotY = Math.PI/4;

var geometry = new THREE.CubeGeometry(1,1,1);
// probably want a sprite material eventually
var material = new THREE.MeshLambertMaterial({color: 0xffffff,
                                              shading: THREE.FlatShading});
var cube = new THREE.Mesh(geometry, material);
scene.add(cube);

var directionalLight = new THREE.DirectionalLight(0xffffff);
directionalLight.position.x = 0.25;
directionalLight.position.y = 0;
directionalLight.position.z = 1;
scene.add(directionalLight);

function render() {
  cube.rotation.x = rotX;
  cube.rotation.y = rotY;
	requestAnimationFrame(render);
	renderer.render(scene, camera);
}

function rotateCube(e){
  var top = canvas.offsetTop;
  var left = canvas.offsetLeft;
  var x = (e.pageX - left);
  var y = (e.pageY - top);
  rotY = (1 - (1.0/w)*(w - x))*Math.PI*0.5;
}

canvas.addEventListener('mousemove', rotateCube, false);

render();
