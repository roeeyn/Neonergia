//requiring path and fs modules
const path = require('path');
const fs = require('fs');
//joining path of directory
const directoryPath = path.join(__dirname, 'Documents');
//passsing directoryPath and callback function
fs.readdir('.', function (err, files) {
    //handling error
    if (err) {
        return console.log('Unable to scan directory: ' + err);
    }
    //listing all files using forEach
    files.forEach(function (file) {
        // Do whatever you want to do with the file
        if(file.includes('drawable')){
          fs.readdir(`./${file}`, (err, nestedFiles) => {
            if (err) {
              return console.log('Unable to scan nested directory: ' + err);
            }

            nestedFiles.forEach((nestedFile) => {
              if(nestedFile.includes('-')){
                console.log(`./${file}/${nestedFile}`)
                const nuevo = nestedFile.replace(/-/g,"_")
                console.log(`./${file}/${nuevo}`)
                fs.rename(`./${file}/${nestedFile}`, `./${file}/${nuevo}`, (err) => console.log(err ? 'VAlio vrg!' : 'ahuevo'))
              }

            })

          })
        }
    });
  })