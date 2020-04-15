package ru.omsu.imit.course3.first.lab5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyConcurrentHashMap<K, V> implements Map<K, V> {
	private HashMap<K, V> hashMap;
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	public MyConcurrentHashMap() {
		hashMap = new HashMap<>();
	}

	@Override
	public int size() {
		lock.readLock().lock();
		try {
			return hashMap.size();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		lock.readLock().lock();
		try {
			return hashMap.isEmpty();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public boolean containsKey(Object key) {
		lock.readLock().lock();
		try {
			return hashMap.containsValue(key);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public boolean containsValue(Object value) {
		lock.readLock().lock();
		try {
			return hashMap.containsValue(value);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public V get(Object key) {
		lock.readLock().lock();
		try {
			return hashMap.get(key);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public V put(K key, V value) {
		lock.writeLock().lock();
		try {
			return hashMap.put(key, value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public V remove(Object key) {
		lock.readLock().lock();
		try {
			return hashMap.remove(key);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		lock.writeLock().lock();
		try {
			hashMap.putAll(m);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void clear() {
		lock.writeLock().lock();
		try {
			hashMap.clear();
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public Set<K> keySet() {
		lock.readLock().lock();
		try {
			return hashMap.keySet();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public Collection<V> values() {
		lock.readLock().lock();
		try {
			return hashMap.values();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		lock.readLock().lock();
		try {
			return hashMap.entrySet();
		} finally {
			lock.readLock().unlock();
		}
	}
}
