import java.beans.*;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {
    public static void main(String[] args) throws Exception {
        ElList elList = new ElList();
        elList.input();
        change("Kiev           ");
        elList.binOut();
        elList.binInput();
   //     elList.XMLout();
        elList.XMLinput();
        elList.DomXml();
        Passwords pass = new Passwords();
        pass.input();
        System.out.println(pass.verify("Innokentiy", "rtweyhj"));
        System.out.println(pass.verify("Avram_Lincoln", "234598273423806-0236218123852098"));
        SimGUI app = new SimGUI();
        app.setVisible(true);
    }

    static void change(String newname) throws IOException
    {
        RandomAccessFile raf = new RandomAccessFile("input.txt", "rw");
        byte[] oldname = new byte[14];
        raf.seek(29);
        raf.read(oldname);
        System.out.printf("Старый путь: " + new String(oldname) + "\n");
        raf.seek(29);
        raf.writeBytes(newname);
        System.out.printf("Новый путь: " + newname + "\n");
        raf.close();
    }
}

abstract class Abscls
{
        public abstract void input() throws IOException;
}

class ElList extends Abscls
{
    ArrayList<Travel> elems = new ArrayList<>();

    public void DomXml() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Travel");
        doc.appendChild(rootElement);
        org.w3c.dom.Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(elems.get(0).getName()));
        rootElement.appendChild(name);
        Element from = doc.createElement("from");
        from.appendChild(doc.createTextNode(elems.get(0).getFrom()));
        rootElement.appendChild(from);
        Element in = doc.createElement("in");
        in.appendChild(doc.createTextNode(elems.get(0).getIn()));
        rootElement.appendChild(in);
        Element length = doc.createElement("length");
        in.appendChild(doc.createTextNode(elems.get(0).getLength()));
        rootElement.appendChild(length);
        Element date = doc.createElement("date");
        date.appendChild(doc.createTextNode(elems.get(0).getDate()));
        rootElement.appendChild(date);
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("file.xml"));
        // Output to console for testing
        // StreamResult result = new StreamResult(System.out);
        transformer.transform(source, result);

        System.out.println("File saved!");
    }

    public void input() throws IOException
    {
        Scanner sc = new Scanner(new File("input.txt"));
        String line;
        while(sc.hasNext())
        {
            line = sc.nextLine();
            elems.add(new Travel(line));
        }
        for(Travel i: elems)
        {
            i.output();
        }
        sc.close();
    }

    void binOut() throws Exception
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BinOutput.txt"));
        oos.writeObject(elems);
        oos.close();
    }

    void binInput() throws Exception
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BinOutput.txt"));
        elems = (ArrayList<Travel>) ois.readObject();
        ois.close();
        for(Travel i: elems)
        {
            i.output();
        }
    }

    void XMLout() throws IOException
    {
        FileOutputStream fos = new FileOutputStream("XMLoutput.txt");
        XMLEncoder xenc = new XMLEncoder(fos);
        xenc.setPersistenceDelegate(Travel.class, new DefaultPersistenceDelegate(new String[]{
                "name",
                "from",
                "in",
                "length",
                "date"
        }));
        xenc.writeObject(elems);
        xenc.close();
        fos.close();
    }

    void XMLinput() throws IOException
    {
        FileInputStream os = new FileInputStream("XMLoutput.txt");
        XMLDecoder dcr = new XMLDecoder(os);
        elems = (ArrayList<Travel>) dcr.readObject();
        dcr.close();
        for(Travel i: elems)
        {
            i.output();
        }
    }

    public void cipher(String fileInput) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        PublicKey pub = pair.getPublic();
        /* Создание объекта класса Signature */
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
        /* Инициализация частным ключом */
        dsa.initSign(priv);
        /* Чтение данных из файла "data". Вызов метода update() */
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileInput));
        byte[] buffer;
        buffer = bis.readAllBytes();
        dsa.update(buffer);
        bis.close();
        /* Генерация подписи */
        byte[] realSig = dsa.sign();
        /* Сохранение подписи в файл "signature" */
        FileOutputStream fos = new FileOutputStream("sign.txt");
        fos.write(realSig);
        fos.close();
        /* Сохранение открытого ключа в файл "pubkey" */
        byte[] key = pub.getEncoded();
        fos = new FileOutputStream("pubKey.txt");
        fos.write(key);
        fos.close();
    }
    public static String[] decipher(String fileInput) throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        /* Получение encoded public key из файла "pubkey" */
        FileInputStream fis = new FileInputStream("pubKey.txt");
        byte[] encKey = fis.readAllBytes();
        fis.close();
        /* Создание спецификации ключа */
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
        /* Создание объектов Keyfactory и Publickey*/
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
        /* Чтение подписи из файла "signature" */
        fis = new FileInputStream("sign.txt");
        byte[] sigToVerify = fis.readAllBytes();
        fis.close();
        /* Создание объекта класса Signature и инициализация с помощью открытого ключа    */
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(pubKey);
        /* Чтение данных из файла "data" и вызов метода update() */
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileInput));
        byte[] buffer;
        String data;
        buffer = bis.readAllBytes();
        data = new String(buffer, StandardCharsets.UTF_8);
        sig.update(buffer);
        bis.close();
        StringTokenizer st = new StringTokenizer(data, "\r\n");
        String[] stData = new String[st.countTokens()];
        for (int i = 0; i < stData.length; i++) {
            stData[i] = st.nextToken();
        }
        if(sig.verify(sigToVerify)) {
            return stData;
        }
        else {
            return null;
        }
    }
}

