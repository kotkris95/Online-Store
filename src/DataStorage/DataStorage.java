package DataStorage;

public interface DataStorage {
    void saveTo(String filename);

    DataStorage loadFrom(String filename);

    public enum Type implements DataStorage {
        FILE(new DSFile()), SERIALIZABLE(new DSSerializable()), RAM(new DSRam());
        private DataStorage dataStorage;

        Type(DataStorage dataStorage) {
            this.dataStorage = dataStorage;
        }

        @Override
        public void saveTo(String filename) {
            dataStorage.saveTo(filename);
        }

        @Override
        public DataStorage loadFrom(String filename) {
            dataStorage.loadFrom(filename);
            return null;
        }
    }
}

