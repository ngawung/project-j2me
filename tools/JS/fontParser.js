// Im to lazy work on binary format... so i just convert it to variable lol

const fs = require("fs");
const parser = require("xml2json");

let input = parser.toJson(fs.readFileSync("font.fnt"), { object: true });
let ws = fs.createWriteStream('font.txt');

ws.write(`{${input.font.info.size}, ${input.font.common.lineHeight}, ${input.font.common.base}},\n`);

input.font.chars.char.forEach(x => {
	ws.write(`{${x.id}, ${x.x}, ${x.y}, ${x.width}, ${x.height}, ${x.xoffset}, ${x.yoffset}, ${x.xadvance}},\n`);
})