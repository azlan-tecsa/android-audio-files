export interface AudioFilesPlugin {
  listAudioFiles(): Promise<{ files: FileIds }>;
}

export interface FileIds {
  [key: string]: FileDetails
}

export interface FileDetails {
  name: string,
  path: string
}