export function convertObjectToBase64(object){
  const json = JSON.stringify(object);
  return Buffer.from(json).toString("base64");
}

export function readObjectFromBase64(base64String) {
  const converted = Buffer.from(base64String, "base64").toString("ascii");
  return JSON.parse(converted);
}
