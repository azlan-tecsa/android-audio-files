import { WebPlugin } from '@capacitor/core';

import type { AudioFilesList, AudioFilesPlugin } from './definitions';

export class AudioFilesWeb extends WebPlugin implements AudioFilesPlugin {
  listAudioFiles(): AudioFilesList {
    return <AudioFilesList>{ files: [] };
  }
}
