package com.costa.luiz.batchprocessing;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.Resource;

public class MultiResourceReaderThreadSafe<T> implements ItemReader<T> {

    private final MultiResourceItemReader<T> delegate;
    private final Object lock = new Object();

    public MultiResourceReaderThreadSafe(MultiResourceItemReader<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public T read() throws Exception {
        synchronized (lock) {
            return delegate.read();
        }
    }

    public void setResources(Resource[] resources) {
        synchronized (lock) {
            delegate.setResources(resources);
        }
    }

    public void open(ExecutionContext executionContext) {
        synchronized (lock) {
            delegate.open(executionContext);
        }
    }

    public void close() {
        synchronized (lock) {
            delegate.close();
        }
    }
}
