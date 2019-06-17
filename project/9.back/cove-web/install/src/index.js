export default uppy = Uppy.Core()
   .use(Uppy.Dashboard, {
     inline: true,
     target: '#drag-drop-area'
   });

uppy.on('complete', (result) => {
console.log('Upload complete! We’ve uploaded these files:', result.successful)
})
