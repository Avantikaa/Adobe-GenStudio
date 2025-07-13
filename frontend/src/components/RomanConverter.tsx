import React, { useState } from "react";
import { TextField, Button, Text, View, Flex } from "@adobe/react-spectrum";
import { API_URL } from "../api";

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

        try {
            const response = await fetch(`/api/romannumeral?query=${inputValue}`);
            console.log(response);
            if (!response.ok) {
                const errorText = await response.text();
                setError(`Error: ${errorText}`);
                return;
            }
            const data = await response.json();
            setResult(data.output);
        } catch (err) {
            setError("Failed to connect to server.");
        }
    };

    return (
        <Flex direction="column" gap="size-200" alignItems="start" maxWidth="size-3600">
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
    );
};

export default RomanConverter;
