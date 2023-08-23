import { WebPlugin } from '@capacitor/core';

import type { FileIds, AudioFilesPlugin } from './definitions';

export class AudioFilesWeb extends WebPlugin implements AudioFilesPlugin {
  async listAudioFiles(): Promise<{ files: FileIds }> {
    return Promise.resolve(<{ files: FileIds }>{ files: {} });
  }
}
