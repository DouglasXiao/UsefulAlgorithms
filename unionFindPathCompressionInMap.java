Map<String, String> map = new HashMap<>();

String findParent(String id) {
	String parent = map.get(id);
	if (!id.equals(parent)) {
		String higherParent = findParent(parent);
		map.put(id, higherParent);
		return findParent(higherParent);
	}
	return id;
}

