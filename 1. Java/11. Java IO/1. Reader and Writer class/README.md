# java.io.Reader and java.io.Writer Class in Java

## Theory

The `java.io.Reader` and `java.io.Writer` classes are part of Java's character-based input and output system. They provide the foundation for reading and writing character streams, which is useful for handling text data. These classes are abstract and have several concrete subclasses that implement specific functionality for different types of input and output sources.

### Key Points

1.  **Reader Class**:

    - **Purpose**: The `Reader` class is the abstract base class for all character input streams.

    - **Constructors**:

      - `protected Reader()`: Creates a new character-stream reader whose critical sections will synchronize on the reader itself.
      - `protected Reader(Object lock)`: Creates a new character-stream reader whose critical sections will synchronize on the given object - `obj`.

    - **Common Methods**:

      1.  `int read()`: Reads a single character.
          - Returns the character read, as an integer in the range 0 to 65535 (0x00-0xffff), or -1 if theend of the stream has been reached
      2.  `int read(char[] cbuf)`: Reads characters into the passed array (cbuf).
          - Returns the number of characters read, or -1 if the end of the stream has been reached.
      3.  `int read(char[] cbuf, int offset, int length)`: Reads characters into a portion of an array.
          - Parameters:
          - cbuf: Destination buffer
          - offset: Offset at which to start storing characters
          - length: Maximum number of characters to read
          - Returns: The number of characters read, or -1 if the end of the stream has been reached
      4.  `void close()`:
          - Closes the stream and releases any system resources associated with it.
          - Once the stream has been closed, further method invocations will throw an `IOException`.
      5.  `void mark(int readAheadLimit)`:
          - Marks the present position in the stream.
          - Subsequent calls to `reset()` will attempt to reposition the stream to this point.
          - Not all character-input streams support the `mark()` operation.
      6.  `boolean markSupported()`: Tells whether this stream supports the mark() operation.
      7.  `void reset()`: Resets the stream.
          - If the stream has been marked, then attempt to reposition it at the mark.
          - If the stream has not been marked, then attempt to reposition it to its starting point.
      8.  `long skip(long n)`: Skips characters.
          - This method will block until some characters are available, an I/O error occurs, or the end of the stream is reached.

    - **Implementations of Reader Class**:

      1. `BufferedReader`: It wraps around another Reader (such as a `FileReader` or an `InputStreamReader`) and provides buffering capabilities.
      2. `FileReader`
      3. `CharArrayReader`
      4. `FilterReader`
      5. `InputStreamReader`
      6. `PipedReader`
      7. `StringReader`

2.  **Writer Class**:

    - **Purpose**: The `Writer` class is the abstract base class for all character output streams.

    - **Constructors**:

      - `protected Writer()`: Creates a new character stream whose critical sections will synchronize on the writer itself.
      - `protected Writer(Object obj)`: Creates a new character stream whose critical sections will synchronize on the given object – `obj`.

    - **Common Methods**:

      1. `void write(int c)`: Writes a single character to the character stream.
      2. `void write(char[] cbuf)`: Writes an array of characters.
      3. `void write(char[] cbuf, int offset, int length)`: Writes a portion of an array of characters.
      4. `void write(String str)`: Writes a string to the character stream.
      5. `void write(String str, int offset, int maxlen)`: Writes some part of the string to the character stream.
      6. `void close()`: Flushes and Closes the stream and releases any system resources associated with it.
      7. `void flush()`: Flushes the Writer stream. Flushing one stream invocation will flush all other buffer in chain.
      8. `append(char ch)`: Appends a single character to the Writer.
      9. `append(CharSequence char_sq)`: Appends specified character sequence to the Writer.
      10. `append(CharSequence char_sq, int start, int end)`: Apends specified part of a character sequence to the Writer.

    - **Implementations of Writer Class**:

      1. `FileWriter`: Used to write characters to a file.
      2. `BufferedWriter`: Provides buffering for Writer instances to make the writing of characters, arrays, and strings more efficient.
      3. `PrintWriter`: Prints formatted representations of objects to a text-output stream.
      4. `StringWriter`: A character stream that collects its output in a string buffer, which can then be used to construct a string.
      5. `CharArrayWriter`: Writes to a character array, which can be converted to a string or a char array.

### Important Subclasses of Reader and Writer

1. **FileReader and FileWriter**:

   - **FileReader**: Reads character data from a file.
   - **FileWriter**: Writes character data to a file.

2. **BufferedReader and BufferedWriter**:

   - **BufferedReader**: Buffers input character data to improve performance.
   - **BufferedWriter**: Buffers output character data to improve performance.

3. **InputStreamReader and OutputStreamWriter**:
   - **InputStreamReader**: Bridges byte streams to character streams (e.g., reading from a byte-based input stream and decoding it to characters).
   - **OutputStreamWriter**: Bridges character streams to byte streams (e.g., encoding characters to bytes and writing to a byte-based output stream).
