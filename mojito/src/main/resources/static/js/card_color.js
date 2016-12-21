var colors=["#FFA500", "#FF8C00", "#FF4500", "#D2691E", "#CD853F", "#F5F5DC", "#FFFF00", "#FFD700",
	"#7CFC00", "#ADFF2F", "#556B2F", "#FFF0F5", "#228B22", "#00FF7F", "#00FA9A", "#008080",
	"#00BFFF", "#00CED1", "#AFEEEE"];

function get_random_integer(min, max){ return Math.floor(Math.random()*(max-min+1))+min;}

console.log(colors[1]);
document.getElementsByClassName("demo-card-event mdl-card mdl-shadow--2dp")[0].style.backgroundColor = colors[get_random_integer(0, 18)];