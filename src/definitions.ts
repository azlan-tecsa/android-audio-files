export interface AudioFilesPlugin {
  listAudioFiles(): AudioFilesList;
}

export interface AudioFilesList {
  files: string[]
}