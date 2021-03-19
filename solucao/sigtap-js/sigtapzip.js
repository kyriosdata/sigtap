const jszip = require("jszip");
const fs = require("fs");

const zipfile = "../../datasus/TabelaUnificada_202103_v2103031426.zip";

class SigtapZip {
  constructor(zipFilename) {
    this.filename = zipFilename;
    this.blob = this.readZip(zipfile);
  }

  async conteudo() {
    const zip = await jszip.loadAsync(this.blob);
    zip.forEach((path) => {
      console.log(path);
    });
  }

  async carregue(entrada) {
    const zip = await jszip.loadAsync(this.blob);
    const conteudo = await zip.file(entrada).async("string");
    console.log(conteudo);
  }

  readZip(zfn) {
    try {
      if (fs.existsSync(zfn)) {
        console.log("Arquivo encontrado...");
        return fs.readFileSync(zfn);
      }
    } catch (err) {
      console.error(err);
      return "erro ao executar SigtapZip.readZip";
    }
  }
}

const sigtap = new SigtapZip(zipfile);

sigtap
  .conteudo()
  .then(() => console.log("concluido"))
  .catch(console.log);

sigtap
  .carregue("LEIA_ME.TXT")
  .then(console.log)
  .catch(() => console.log("erro"));
