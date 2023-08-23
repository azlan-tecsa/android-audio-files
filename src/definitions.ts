export interface AudioFilesPlugin {
  listAudioFiles(): Promise<{ [id: string]: FileDetails }>;
}

export interface FileDetails {
  name: string,
  relative_path: string,
  full_path: string
}