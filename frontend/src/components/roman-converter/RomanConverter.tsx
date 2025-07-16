import React, { useState } from "react";
import { TextField, Button, Text, View, Flex } from "@adobe/react-spectrum";
import { fetchRomanNumeral } from "../../api/roman";

const RomanConverter: React.FC = () => {
    const [inputValue, setInputValue] = useState<string>("");
    const [result, setResult] = useState<string | null>(null);
    const [error, setError] = useState<string | null>(null);

    const handleConvert = async () => {
        setError(null);
        setResult(null);

        if (!inputValue || isNaN(Number(inputValue))) {
            setError("Please enter a valid number.");
            return;
        }

        if (Number(inputValue)<1 || Number(inputValue)>3999) {
            setError("Please enter a valid number between 1 and 3999");
            return;
        }

        try {
            const response = await fetchRomanNumeral(inputValue);
            console.log("Roman Numeral: " +response);
            setResult(response);
        } catch (err) {
            setError("Failed to connect to server");
        }
    };

    return (
        <Flex
            direction="column"
            alignItems="center"
            justifyContent="center"
            height="100vh"  // take full viewport height
        >
            <Flex direction="column" gap="size-200" alignItems="center" maxWidth="size-3600">
                <TextField
                    label="Enter a number (1-3999)"
                    value={inputValue}
                    onChange={setInputValue}
                    type="number"
                    width="size-3000"
                />
                <Button variant="cta" onPress={handleConvert}>
                    Convert to Roman Numeral
                </Button>

                {result && (
                    <View backgroundColor="positive" padding="size-200" borderRadius="regular">
                        <Text>Roman Numeral: <strong>{result}</strong></Text>
                    </View>
                )}

                {error && (
                    <View backgroundColor="negative" padding="size-200" borderRadius="regular">
                        <Text>{error}</Text>
                    </View>
                )}
            </Flex>
        </Flex>
    );
};

export default RomanConverter;
