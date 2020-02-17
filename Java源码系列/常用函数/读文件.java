
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



PrintWriter pw = new PrintWriter("output\\" + fileName + ".out", "UTF-8");
pw.println();
pw.print();

pw.close();





private static void WriteIntoFile(String fileName) throws FileNotFoundException {
    try {
        Output = new ArrayList<>();

        System.out.println("\n------output " + fileName);
        PrintWriter pw = new PrintWriter("output\\" + fileName + ".out", "UTF-8");

        pw.println(Output.size());
        System.out.println(Output.size());

        for (int i = 0; i < Output.size(); i++) {
            pw.print(Output.get(i) + " ");
            System.out.println(Output.get(i) + " ");
        }
        pw.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}