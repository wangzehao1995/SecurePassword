package wzhkun.securepw.bl;

import java.io.IOException;

public interface IOStreamSupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws IOException;
}
