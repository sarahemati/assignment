package ir.asanpardakht.practice.dao;

import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import ir.asanpardakht.practice.model.BasicEntity;

public class FileDatabase<T extends BasicEntity> {
	private final String FILE_NAME = "database.db";

	public void append(T t) throws Exception {
		RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rws");
		long fileLength = file.length();
		file.seek(fileLength);
		file.writeBytes(System.lineSeparator() + t.toString());
		file.close();
	}

	public void append(List<T> list) throws Exception {
		String data = list.stream().map(d -> d.toString()).collect(Collectors.joining(System.lineSeparator()));
		RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rws");
		long fileLength = file.length();
		file.seek(fileLength);
		file.writeBytes(data);
		file.close();
	}

	public void replace(T t) throws Exception {
		Files.delete(Path.of(FILE_NAME));
		RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rws");
		file.writeBytes(t.toString() + System.lineSeparator());
		file.close();
	}

	public void replace(List<T> list) throws Exception {
		Files.delete(Path.of(FILE_NAME));
		String data = list.stream().map(d -> d.toString()).collect(Collectors.joining(System.lineSeparator()));
		RandomAccessFile file = new RandomAccessFile(FILE_NAME, "rws");
		file.writeBytes(data);
		file.close();
	}

	public String find(int id) throws Exception {
		RandomAccessFile file = new RandomAccessFile(FILE_NAME, "r");
		file.seek(0);
		while (true) {
			String result = file.readLine();
			if (result == null) {
				break;
			}
			if (result.toLowerCase().contains("\"id\":" + id)) {
				return result;
			}
		}
		file.close();
		return null;
	}

}
