export interface AudioFilesPlugin {
  listAudioFiles(): Promise<{ [id: string]: FileDetails }>;
}

export interface FileDetails {
  name: string,
  artist: string,
  relative_path: string,
  full_path: string
}