var _require = require('../components/icons'),
    iconFile = _require.iconFile,
    iconText = _require.iconText,
    iconAudio = _require.iconAudio,
    iconVideo = _require.iconVideo,
    iconPDF = _require.iconPDF;

module.exports = function getIconByMime(fileType) {
  var defaultChoice = {
    color: '#838999',
    icon: iconFile()
  };

  if (!fileType) return defaultChoice;

  var fileTypeGeneral = fileType.split('/')[0];
  var fileTypeSpecific = fileType.split('/')[1];

  if (fileTypeGeneral === 'text') {
    return {
      color: '#5a5e69',
      icon: iconText()
    };
  }

  if (fileTypeGeneral === 'audio') {
    return {
      color: '#068dbb',
      icon: iconAudio()
    };
  }

  if (fileTypeGeneral === 'video') {
    return {
      color: '#19af67',
      icon: iconVideo()
    };
  }

  if (fileTypeGeneral === 'application' && fileTypeSpecific === 'pdf') {
    return {
      color: '#e25149',
      icon: iconPDF()
    };
  }

  if (fileTypeGeneral === 'image') {
    return {
      color: '#f2f2f2',
      icon: ''
    };
  }

  return defaultChoice;
};