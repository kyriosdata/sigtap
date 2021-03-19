const jszip = require("jszip");
const fs = require("fs");

const zipfile = "../../datasus/TabelaUnificada_202103_v2103031426.zip";

async function getConteudo(zfn) {
  const zip = await jszip.loadAsync(zfn);
  zip.forEach((path, entry) => {
    console.log(path);
  });
}

function readZip(zfn) {
  try {
    if (fs.existsSync(zfn)) {
      console.log("Arquivo encontrado...");
      return fs.readFileSync(zfn);
    }
  } catch (err) {
    console.error(err);
    return undefined;
  }
}

const blob = readZip(zipfile);
getConteudo(blob)
  .then(() => console.log("concluido"))
  .catch(console.log);
