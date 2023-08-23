import { WebPlugin } from '@capacitor/core';

import type { FileDetails, AudioFilesPlugin } from './definitions';

export class AudioFilesWeb extends WebPlugin implements AudioFilesPlugin {
  async listAudioFiles(): Promise<{ [id: string]: FileDetails }> {
    return Promise.resolve({});
  }
}
