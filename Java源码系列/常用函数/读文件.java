
BufferedReader br = new BufferedReader(new FileReader("fileName"));
String line;
while ((line = br.readLine()) != null) {
    //handle
}
br.close();





private static void getInputFromFile(String fileName) throws FileNotFoundException {
    try {
        Input = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("input\\" + fileName));
        String line;

        while ((line = br.readLine()) != null) {
            String[] letters = line.split(" ");
            for (String letter : letters) {
                Input.add(Integer.parseInt(letter));
            }
        }
        br.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}