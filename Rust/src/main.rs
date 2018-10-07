struct Color {
    red : u8,
    green : u8,
    blue : u8
}

struct Kilometers(i32);

enum FlashMessage {
  Success, //a unit variant
  Warning{ category: i32, message: String }, //a struct variant
  Error(String) //a tuple variant
}

fn main() {
    println!("In Main!");
    printfunction1();
    printfunction2();
    CallReturnValue();
    ControlFlow(15);
    Match();
    WhileLoop();
    LabeledWhileLoop();
    ForLoops();
    Vectors();
    ColorFunctions();
    TupleStruct();
    EnumWorking();
}

fn printfunction2()
{
    println!("this is printFunction2()");
}

fn printfunction1()
{
    println!("This is printfunction1()");
    println!("{}, {}!", "Hello", "world"); // Hello, world!
    println!("{0}, {1}!", "Hello", "world"); // Hello, world!
    println!("{greeting}, {name}!", greeting="Hello", name="world"); // Hello, world!

    println!("{:?}", [1,2,3]); // [1, 2, 3]
    println!("{:#?}", [1,2,3]);

    let y = format!("{:#?}", [1,2,3,4,5]);
    println!("{}",y);
    /*
        [
            1,
            2,
            3
        ]
    */

    // 🔎 format! macro is used to store the formatted STRING
    let x = format!("{}, {}!", "Hello", "world");
    println!("{}", x); // Hello, world!
}

fn returnValue(a: i32) -> i32{
    a + 2
}

fn CallReturnValue() {
    let b = returnValue; //function pointers
    let c: fn(i32) -> i32 = returnValue;

    println!("{}", b(5));
    println!("{}", c(10));
}


fn  ControlFlow(team_size: i32) {
    if team_size < 5 {
    println!("Small");
    } else if team_size < 10 {
        println!("Medium");
    } else {
        println!("Large");
    }
}

fn Match() {
    let is_allowed = false;
    let list_type = match is_allowed {
        true => "Full",
        false => "Restricted"
        // no default/ _ condition can be skipped
        // Because data type of is_allowed is boolean and all possibilities checked on conditions
    };
    println!("{}", list_type); // Restricted


    let marks_paper_a: u8 = 25;
    let marks_paper_b: u8 = 30;
    let output = match (marks_paper_a, marks_paper_b) {
        (50, 50) => "Full marks for both papers",
        (50, _) => "Full marks for paper A",
        (_, 50) => "Full marks for paper B",
        (x, y) if x > 25 && y > 25 => "Good",
        (_, _) => "Work hard"
    };
    println!("{}", output); 
}

fn WhileLoop() {
    let mut b = 0;
    while b < 5 {
        if b == 0 {
            println!("Skip value : {}", b);
            b += 1;
            continue;
        } else if b == 2 {
            println!("Break At : {}", b);
            break;
        }
        println!("Current value : {}", b);
        b += 1;
    }
}

fn LabeledWhileLoop() {
    let mut c1 = 1;
    'outer_while: while c1 < 6 { //set label outer_while
        let mut c2 = 1;
        'inner_while: while c2 < 6 {
            println!("Current Value : [{}][{}]", c1, c2);
            if c1 == 2 && c2 == 2 { break 'outer_while; } //kill outer_while
            c2 += 1;
        }
        c1 += 1;
    }
}
fn ForLoops() {
    println!("This is forLoop function");
    for b in 0..6 {
    if b == 0 {
        println!("Skip Value : {}", b);
        continue;
    } else if b == 2 {
        println!("Break At : {}", b);
        break;
    }
    println!("Current value : {}", b);
    }

    // Outer break
    'outer_for: for c1 in 1..6 { //set label outer_for
        'inner_for: for c2 in 1..6 {
            println!("Current Value : [{}][{}]", c1, c2);
            if c1 == 2 && c2 == 2 { break 'outer_for; } //kill outer_for
        }
    }
}

fn Vectors()
{
    // A vector is kind of a re-sizable array but all elements must be in the same type.
    //  Vectors always allocate their data in dynamically allocated heap.

    println!("I am in Vectors");
    let mut c = vec![5, 4, 3, 2, 1];
    c[0] = 1;
    c[1] = 2;
    //c[6] = 2; can't assign values this way, index out of bounds
    println!("{:?}", c);
}

fn ColorFunctions() {
  // creating an instance
  let black = Color {red: 0, green: 0, blue: 0};

  // accessing its fields using dot notation
  println!("Black = rgb({}, {}, {})", black.red, black.green, black.blue); //Black = rgb(0, 0, 0)

  // structs are immutable by default, use `mut` to make it mutable but doesn't support field level mutability
  let mut link_color = Color {red: 0,green: 0,blue: 255};
  link_color.blue = 238;
  println!("Link Color = rgb({}, {}, {})", link_color.red, link_color.green, link_color.blue); //Link Color = rgb(0, 0, 238)

  // copy elements from another instance
  let blue = Color {blue: 255, .. link_color};
  println!("Blue = rgb({}, {}, {})", blue.red, blue.green, blue.blue); //Blue = rgb(0, 0, 255)

  // destructure the instance using a `let` binding, this will not destruct blue instance
  let Color {red: r, green: g, blue: b} = blue;
  println!("Blue = rgb({}, {}, {})", r, g, b); //Blue = rgb(0, 0, 255)

  // creating an instance via functions & accessing its fields
  let midnightblue = get_midnightblue_color();
  println!("Midnight Blue = rgb({}, {}, {})", midnightblue.red, midnightblue.green, midnightblue.blue); //Midnight Blue = rgb(25, 25, 112)

  // destructure the instance using a `let` binding
  let Color {red: r, green: g, blue: b} = get_midnightblue_color();
  println!("Midnight Blue = rgb({}, {}, {})", r, g, b); //Midnight Blue = rgb(25, 25, 112)
}

fn get_midnightblue_color() -> Color {
    Color {red: 25, green: 25, blue: 112}
}

fn TupleStruct() {
  //newtype pattern
  let distance = Kilometers(20);
  // destructure the instance using a `let` binding
  let Kilometers(distance_in_km) = distance;
  println!("The distance: {} km", distance_in_km); //The distance: 20 km
}

fn EnumWorking() {
  let mut form_status = FlashMessage::Success;
  print_flash_message(form_status);

  form_status = FlashMessage::Warning {category: 2, message: String::from("Field X is required")};
  print_flash_message(form_status);

  form_status = FlashMessage::Error(String::from("Connection Error"));
  print_flash_message(form_status);
}

fn print_flash_message(m : FlashMessage) {
  // pattern matching with enum
  match m {
    FlashMessage::Success =>
      println!("Form Submitted correctly"),
    FlashMessage::Warning {category, message} => //Destructure, should use same field names
      println!("Warning : {} - {}", category, message),
    FlashMessage::Error(msg) =>
      println!("Error : {}", msg)
  }
}